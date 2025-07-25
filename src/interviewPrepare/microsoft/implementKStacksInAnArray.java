package interviewPrepare.microsoft;

//Given an array of size n, the task is to implement k stacks using a single array. We mainly need to perform the following type of queries on the stack.
//push(x, i) :  This operations pushes the element x into stack i
//pop(i) : This operation pops the top of stack i

//Here i varies from 0 to k-1
public class implementKStacksInAnArray {

    static class stack2{
        int[] arr;
        int[] pointers;
        int n;
        int k;
        public stack2(int n, int k){
            this.n = n;
            this.k = k;
            arr = new int[n];
            pointers = new int[k];
            int stackSize = n/k;
            for(int i = 0, p = 0; i < k; i++, p+=stackSize){
                pointers[i] = p;
            }
        }

        public void push(int x, int i){
            arr[pointers[i]] = x;
            pointers[i]++;
        }

        public int pop(int i) {
            return arr[--pointers[i]];
        }
    }

    public static void main(String[] args){
        stack2 test = new stack2(12, 3);
        test.push(19, 2);
        test.push(1, 1);
        test.push(193, 2);
        test.push(9, 0);
        test.push(29, 2);
        System.out.println(test.pop(2));
        System.out.println(test.pop(0));
        System.out.println(test.pop(1));
    }
}
