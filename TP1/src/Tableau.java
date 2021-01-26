class Tableau {
    public static int[]  gennerateArray(int len,int max) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public static void triRapid(int[] array) {
        int len = array.length;
        triRapidAux(array, 0, len - 1);
    }

    private static void triRapidAux(int[] array, int begin, int end) {
        if (begin < end) {
            int pivot = partition(array, begin, end);
            triRapidAux(array, begin, pivot - 1);
            triRapidAux(array, pivot + 1, end);
        }
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = begin-1;

        for(int j = begin; j < end;j++){
            if(array[j] < pivot){
                i = i+1;

                int temp = array[j];
                array[j]= array[i];
                array[i] = temp;
            }
        }

        if (pivot < array[i+1]){
            array[end]= array[i+1];
            array[i+1] = pivot;
        }

        return i+1;
    }

    public static void printArray(int[] intArray){
        for (int index = 0; index < intArray.length ; index++) {
            if(index == intArray.length - 1) {
                System.out.println(intArray[index]);
            } else {
                System.out.print(intArray[index] + ", ");
            }
        }
    }

    public static void main(String[] args) {
        int[] Array = gennerateArray(10,100);

        System.out.println("Tableau avant tri rapide :");
        printArray(Array);
        triRapid(Array);
        System.out.println("Tableau après tri rapide  :");
        printArray(Array);
    }
}
