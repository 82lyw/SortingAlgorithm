import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {
    public static void main (String[] args) {

        int [] arr = new int[]{5,4,2,3,8};

        System.out.println("冒泡"+Arrays.toString(BubbleSort(arr)));
        int [] arr2 = new int[]{5,4,2,3,8};
        System.out.println("选择"+Arrays.toString(SelectionSort(arr2)));
        int [] arr3 = new int[]{5,4,2,3,8};
        System.out.println("插入"+Arrays.toString(InsertSort(arr3)));
        int [] arr4 = new int[]{8,9,1,7,2,3,5,4,6,0};
        System.out.println("希尔"+Arrays.toString(ShellSort(arr4)));

        int [] arr5 = new int[]{9,5,8,4,7,3,6,2};
        System.out.println("归并"+Arrays.toString(MergeSort(arr5)));

        int [] arr6 = new int[]{65,58,10,57,62,13,23,106,78,95,85};
        System.out.println("快排"+Arrays.toString(QuickSort(arr6,0,arr6.length-1)));

//        int [] arr7 = new int[]{4,6,3,5,9};
        int [] arr7 = new int[]{65,58,10,57,62,13,23,106,78,95,85};
        System.out.println("堆排序"+Arrays.toString(HeapSort(arr7)));

        int [] arr8 = new int[]{65,58,10,57,62,13,23,106,78,95,85};
        System.out.println("计数排序"+Arrays.toString(CountSort(arr8)));

        int [] arr9 = new int[]{65,58,10,57,62,13,23,106,78,95,85};
        System.out.println("桶排序"+Arrays.toString(BucketSort(arr9)));

        int [] arr10 = new int[]{4,32,457,0,16,28,64};
        System.out.println("基数排序"+Arrays.toString(RadixSort(arr10)));
    }



    public static int[] BubbleSort(int[] nums) {

        for(int i=1;i<nums.length;i++) {
            for(int j=0;j<nums.length-i;j++) {
                if(nums[j]>nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }

    public static int[] SelectionSort(int[] nums) {
        for(int i=0;i<nums.length-1;i++) {
            int min = i;
            for(int j=i+1;j< nums.length;j++) {
                if (nums[j]<nums[min]) {
                    min = j;
                }
            }

            if(min!=i) {
                int temp = nums[i];
                nums[i]=nums[min];
                nums[min] = temp;
            }

        }
        return nums;
    }

    public static int[] InsertSort(int[] nums) {
        for(int i=1;i< nums.length;i++) {
            int temp = nums[i];
            int j=i;
            while(j>0&&temp<nums[j-1]) {
                nums[j] = nums[j-1];
                j--;
            }

            if(j!=i) {
                nums[j] = temp;
            }
        }
        return nums;
    }

    public static int[] ShellSort(int[] nums) {
        for(int gap= nums.length/2;gap>0;gap/=2) {
            for(int i=gap;i< nums.length;i++) {
                int temp = nums[i];
                int j = i-gap;
                while(j>=0&&nums[j]>temp) {
                    nums[j+gap] = nums[j];
                    j -= gap;
                }
                nums[j+gap] = temp;
            }

            //System.out.println(Arrays.toString(nums));
        }
        return nums;
    }

    public static int[] MergeSort(int[] nums) {
        if(nums.length<2) return nums;

        int mid = nums.length/2;
        int[] left = Arrays.copyOfRange(nums,0,mid);
        int[] right = Arrays.copyOfRange(nums,mid,nums.length);
        //System.out.println("left:"+Arrays.toString(left));
        //System.out.println("right:"+Arrays.toString(right));
        return merge(MergeSort(left),MergeSort(right));
    }

    public static int[] merge(int[] left,int[] right) {
        //System.out.println(left.length+","+ right.length);
        int[] result = new int[left.length+ right.length];

        int l=0,r=0,k=0;
        while(l<left.length&&r<right.length) {
            if(left[l]<right[r]) {
                result[k] = left[l];
                k++;
                l++;
            } else if (left[l]==right[r]) {
                result[k]=left[l];
                result[k+1]=right[r];
                k=k+1;
                l++;
                r++;
            }
            else {
                result[k]=right[r];
                k++;
                r++;
            }
        }

        while(l<left.length) {
            result[k] = left[l];
            k++;
            l++;
        }

        while(r<right.length){
            result[k]=left[r];
            k++;
            r++;
        }

        //System.out.println(Arrays.toString(result));

        return result;
    }

    public static int[] QuickSort(int[] nums,int left,int right) {
        if(left<right) {
            int partitionIndex = partition(nums,left,right);
            QuickSort(nums,left,partitionIndex-1);
            QuickSort(nums,partitionIndex+1,right);
        }
        return nums;
    }

    public static int partition(int[] nums, int left,int right) {
        int pivot = left;

        int index=pivot+1;
        for(int i=index;i<=right;i++) {
            if(nums[i]<nums[pivot]) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
        int tmp = nums[pivot];
        nums[pivot] = nums[index-1];
        nums[index-1] = tmp;
//        System.out.println(Arrays.toString(nums));
//        System.out.println(left+","+right);
//        System.out.println(index-1);
        return index-1;
    }

    public static int[] HeapSort(int[] nums) {
        int len = nums.length;
        buildMaxHeap(nums,len);
        System.out.println(Arrays.toString(nums));
        for(int i= len-1;i>0;i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            len--;
            heapify(nums,0,len);
            //System.out.println(Arrays.toString(nums));
        }
        return nums;
    }

    public static void buildMaxHeap(int[] nums,int len) {
        for(int i=(len-2)/2;i>=0;i--) {
            heapify(nums,i,len);
        }
    }

    public static void heapify(int[] nums,int i, int len) {
        int left = 2*i+1;
        int right = 2*i+2;
        int max = i;

        if(left<len && nums[left]>nums[max]) {
            max = left;
        }
        if(right<len && nums[right]>nums[max]) {
            max = right;
        }
        if(max!=i) {
            int temp = nums[max];
            nums[max] = nums[i];
            nums[i] = temp;
            heapify(nums,max,len);
        }

    }

    public static int[] CountSort(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for(int i=1;i< nums.length;i++) {
            if(nums[i]>max) {
                max = nums[i];
            }
            else if(nums[i]<min) {
                min = nums[i];
            }
        }
        int newLen = max - min + 1;
        int[] array = new int[newLen];
        for(int i=0;i< nums.length;i++) {
            array[nums[i]-min]++;
        }

        int j=0;
        for(int i=0;i<newLen;i++) {
            while(array[i]>0) {
                nums[j] = i+min;
                j++;
                array[i]--;
            }
        }
        return nums;
    }

    public static int[] BucketSort(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for(int i=1;i< nums.length;i++) {
            if(nums[i]>max) {
                max = nums[i];
            }
            else if(nums[i]<min) {
                min = nums[i];
            }
        }
        int size = (max-min)/nums.length+1;
        int cnt = (max-min)/size+1;

        List<Integer>[] buckets = new List[cnt];
        for(int i=0;i<cnt;i++) {
            buckets[i] = new ArrayList<>();
        }
        for(int i=0;i<nums.length;i++) {
            buckets[(nums[i]-min)/size].add(nums[i]);
        }
        int index =0;
        for(int i=0;i<cnt;i++) {
            buckets[i].sort(null);
            for(int j=0;j<buckets[i].size();j++) {
                nums[index] = buckets[i].get(j);
                index++;
            }

        }


        return nums;
    }

    public static int[] RadixSort(int[] nums) {
        int max = nums[0];
        for(int i=1;i< nums.length;i++) {
            if(nums[i]>max) {
                max = nums[i];
            }
        }
        int maxLen = (max+"").length();

        List<Integer>[] temps = new List[10];

        for(int i=0,n=1;i<maxLen;i++,n*=10) {
            for(int k=0;k<10;k++) {
                temps[k] = new ArrayList<>();
            }
            for(int j=0;j< nums.length;j++) {
                int rem = nums[j]/n%10;
                temps[rem].add(nums[j]);
            }
            int index = 0;
            for(int j=0;j<10;j++) {
                for(int k=0;k<temps[j].size();k++) {
                    nums[index] = temps[j].get(k);
                    index++;
                }
            }
        }
        return nums;
    }


}
