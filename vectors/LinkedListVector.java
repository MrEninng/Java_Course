package vectors;

public class LinkedListVector implements IVector {

    public LinkedListVector() {
        mSize = 0;

        mHead.prev = mHead;
        mHead.next = mHead;
        mTail = mHead;
    }

    public void addElement(double val) {
        if (mSize == 0) {
            mHead.value = val;
            ++mSize;
            return;
        }
        Node tmp = mTail;  //kek
        mTail.next = new Node(); //sas

        mTail = mTail.next;

        mTail.value = val;
        mTail.prev = tmp;
        ++mSize;

    }

    public void deleteElement(int i) {

        if (i >= mSize || i < 0) {
//            throw expetion;
            return;
        }
        Node tmp = gotoNumber(i);

        if (i == 0) {
            mHead = tmp.next;
            --mSize;
            current = mHead;
            currentIndex = 0;
            return;
        }
        Node prev = tmp.prev;
        prev.next = tmp.next;

        --mSize;
        currentIndex--;
        current = prev;
    }

    @Override
    public int getSize() {
        return mSize;
    }

    @Override
    public void setElement(int i, double val) {
        if (i >= mSize || i < 0) {
//            throw exception;
            return;
        }
        gotoNumber(i);
        current.value = val;
    }

    @Override
    public double getElement(int i) {
        if (i >= mSize || i < 0) {
//            throw exception;
        }
        gotoNumber(i);
        return current.value;
    }
    @Override
    public double getNorm() {
        double norm;
        Node tmp = mHead;
        double sum = 0;
        for (int i = 0; i < mSize; ++i) {
            sum += tmp.value * tmp.value;
            tmp = tmp.next;
        }
        norm = Math.sqrt(sum);
        return norm;
    }


    private int mSize;


    //Вспомогательный внутренний класс, реализует элемент связного списка.
    private class Node {
        //Значение, которое хранит элемент связного списка.
        double value = Double.NaN;
        //Ссылка на предыдущий элемент связного списка.
        Node prev = null;
        //Ссылка на следующий элемент связного списка.
        Node next = null;
    }
    //Ссылка на голову связного списка.
    private Node mHead = new Node();
    private Node mTail;
    //Текущая длина связного списка.
    //Ссылка на последний использовавшийся элемент связного списка.
    private Node current = mHead;



    /*Номер последнего использовавшиегося элемента связного списка. */
    private int currentIndex = 0;
    /*Вспомогательный метод доступа к элементу списка.
    Должен использоваться для доступа из всех остальных методов, т.к. реализует
    механизм "памяти". index - номер требующегося элемента*/
    private Node gotoNumber(int index) {
        if ((index < mSize) && (index >= 0)) {
            if (index == currentIndex) {
                return current;
            }

            if (index > currentIndex) {
                while (index != currentIndex) {
                    current = current.next;
                    ++currentIndex;
                }
                return current;
            } else if (index < currentIndex){
                while (index != currentIndex) {
                    current = current.prev;
                    --currentIndex;
                }
                return current;
            }
        } else {
//            //throw new VectorIndexOutOfBoundsException();
        }

        return null;
    }
}
