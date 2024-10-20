import java.lang.Math;;

public class Hashmap {
    public KeyValuePair[] array;
    public PrimeNumberGenerator prime = new PrimeNumberGenerator();

    @Override
    public String toString() {
        String res = String.valueOf(prime.currentPrime());
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += "\n";
            }
            res += i + "\t";
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res;
    }

    public String toStringOneLine() {
        String res = String.valueOf(prime.currentPrime()) + "[";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += ",";
            }
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res + "]";
    }

    public boolean isPrime(int num) {
        int i, mid = 0;
        boolean flag = false;
        mid = num / 2;
        if (num == 0 || num == 1) {
            return flag;
        } else {
            for (i = 2; i <= mid; i++) {
                if (num % i == 0) {
                    return flag;
                }
            }
            flag = true;
            return flag;
        }
    }

    public int nextBest(int num) {
        int primeNum = num;
        while (!isPrime(primeNum)) {
            primeNum++;
        }
        return primeNum;
    }

    public Hashmap() {
        array = new KeyValuePair[1];
        prime = new PrimeNumberGenerator();
    }

    public Hashmap(String inp) {
        String pNum = inp.substring(0, inp.indexOf('['));
        int p = Integer.valueOf(pNum);// fix
        prime = new PrimeNumberGenerator();
        if (!isPrime(p)) {
            p = nextBest(p);
        }
        prime.head.value = p;
        int size = 1;
        // calculate size of array
        for (int i = 0; i < inp.length(); i++) {
            if (inp.charAt(i) == ',') {
                size++;
            }
        }
        inp = inp.substring(inp.indexOf('[') + 1, inp.length());

        array = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            if (inp.charAt(0) == '-')// value at array must be null
            {
                array[i] = null;
            } else// get student data
            {
                int u = inp.indexOf('u');
                int colon = inp.indexOf(':');
                int percent = inp.indexOf('%');
                // get student number
                String stdNum = inp.substring(u + 1, colon);
                // get student mark
                String mark = inp.substring(colon + 1, percent);
                int sN = Integer.valueOf(stdNum);
                int m = Integer.valueOf(mark);
                KeyValuePair key = new KeyValuePair(sN, m);
                array[i] = key;
            }

            if (inp.length() < 4)// last element is ,-]
            {
                inp = inp.substring(0, 1);
            } else {
                int start = inp.indexOf(',', 0);
                inp = inp.substring(start + 1, inp.length());
            }

        }
    }

    public int hash(int studentNumber) {
        int len = array.length;
        String stdNum = String.valueOf(studentNumber);

        int hashVal = 0;
        for (int i = 0; i < stdNum.length(); i++)
            hashVal = prime.currentPrime() * hashVal + Integer.parseInt(String.valueOf(stdNum.charAt(i)));

        if (hashVal < 0)
            hashVal = Math.abs(hashVal);

        hashVal = hashVal % len;
        return hashVal;
    }

    public KeyValuePair[] copyArray(KeyValuePair[] cur) {
        KeyValuePair[] temp = new KeyValuePair[cur.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cur[i];
        }
        return temp;
    }

    public KeyValuePair search(int studentNumber) {
        int pos = hash(studentNumber);
        if (array[pos] != null && array[pos].studentNumber == studentNumber) {
            return array[pos];
        }
        // student number not found -> try other indexes

        int hash = pos;
        // quadratic probing algorithm:
        int index = searchProbing(hash);
        if (index != -1 && array[index] != null && array[index].studentNumber == studentNumber) {
            return array[index];
        }
        return null;
    }

    public void insert(int studentNumber, int mark) {
        if (array == null) {
            return;
        }
        KeyValuePair exists = search(studentNumber);
        if ((exists != null) && (exists.studentNumber == studentNumber)) {
            exists.mark = mark;
            return;
        }

        KeyValuePair kv = new KeyValuePair(studentNumber, mark);
        insertHelper(kv, studentNumber, mark);
    }

    private int searchProbing(int hash) {
        int index;
        for (int i = 1; i <= 9; i++) {
            switch (i) {
                case 1:
                case 4:
                case 9:
                    index = Math.abs(hash + i * prime.currentPrime()) % array.length;
                    if (array[index] != null)
                        return index;
                    break;
                default:
                    index = Math.abs(hash - i * prime.currentPrime()) % array.length;
                    if (array[index] != null)
                        return index;
                    break;
            }
        }
        return -1;
    }

    private int Search2(int studentNumber) {
        int pos = hash(studentNumber);
        if (array[pos] != null && array[pos].studentNumber == studentNumber) {
            return pos;
        }

        int cp = prime.currentPrime();
        int hash = pos;

        // quadratic probing algorithm:
        for (int i = 1; i < (array.length - 1) / 2; i++) {
            hash = (int) Math.abs(hash + Math.pow((-1), (i - 1)) * cp) % array.length;
            if (array[hash] != null && array[hash].studentNumber == studentNumber) {
                return hash;
            }
        }
        return -1;
    }

    private void insertHelper(KeyValuePair kv, int studentNumber, int mark) {

        int pos = hash(studentNumber);
        if (array[pos] == null) {
            array[pos] = kv;
            return;
        }
        int hash = pos;

        boolean arrayFull = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                arrayFull = false;
            }
        }

        if (!arrayFull) {
            int index = probing(hash);
            if (index != -1) {
                array[index] = kv;
                return;
            }
        }

        KeyValuePair[] temp = copyArray(array);
        int oldSize = temp.length;
        int newSize = array.length * 2;
        array = new KeyValuePair[newSize];
        // add old elements to array
        prime.nextPrime();
        for (int i = 0; i < oldSize; i++) {
            if (temp[i] != null)
                insert(temp[i].studentNumber, temp[i].mark);
        }
        // call nextPrime on prime member
        // try inserting data again:
        insert(studentNumber, mark);
    }

    private int probing(int hash) {
        int index;
        for (int i = 1; i <= 9; i++) {
            switch (i) {
                case 1:
                case 4:
                case 9:
                    index = Math.abs(hash + i * prime.currentPrime()) % array.length;
                    if (array[index] == null)
                        return index;
                    break;
                default:
                    index = Math.abs(hash - i * prime.currentPrime()) % array.length;
                    if (array[index] == null)
                        return index;
                    break;
            }
        }
        return -1;
    }

    public void remove(int studentNumber) {
        KeyValuePair target = search(studentNumber);
        if (target != null) {
            int index = Search2(studentNumber);
            if (index >= 0 && array[index].studentNumber == studentNumber)
                array[index] = null;
        }
    }

}
