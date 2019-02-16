package Calculo_Disponibilidad.Cola;

import java.util.ArrayList;
import java.util.Comparator;

public class Cola {

    ArrayList<TipoCola> queue = new ArrayList<>();

    public Cola(){

    }

    public void enqueueNoOrdenate(TipoCola a){

        queue.add(a);
    }

    public void Ordenate(){


        QuickSort(0, queue.size() - 1);
    }

    public void enqueue(TipoCola a){
        queue.add(a);
        QuickSort(0, queue.size() - 1);
    }
    public void enquueueF (TipoCola a){

        queue.add(a);
        QuickSortF(0, queue.size() - 1);
    }

    public TipoCola dequeue(){

        TipoCola a = queue.get(0);
        queue.remove(0);
        return a;
    }

    private void QuickSortF(int start, int length){

        if(start < length){
            num a = ParticioF(start, length);
            QuickSort(start, a.getRight());
            QuickSort(a.getLeft(), length);
        }

    }

    private num ParticioF(int start, int length){
        int mig = (start + length)/2;
        TipoCola pivot = queue.get(mig);
        TipoCola tmp;
        int left = start;
        int right = length;


        while (left <= right){
            while(queue.get(left).getFiability() > pivot.getFiability()){
                left++;
            }
            while (queue.get(right).getFiability() < pivot.getFiability()){
                right--;
            }
            if (left < right){
                tmp = queue.get(left);

                queue.set(left, queue.get(right));
                queue.set(right, tmp);
                left++;
                right--;

            }else{
                if(left == right){
                    left++;
                    right--;
                }

            }
        }
        num a = new num();
        a.setLeft(left);
        a.setRight(right);

        return a;

    }

    private void QuickSort(int start, int length){

        if(start < length){
            num a = Particio(start, length);
            QuickSort(start, a.getRight());
            QuickSort(a.getLeft(), length);
        }

    }

    private num Particio(int start, int length){
        int mig = (start + length)/2;
        TipoCola pivot = queue.get(mig);
        TipoCola tmp;
        int left = start;
        int right = length;


        while (left <= right){
            while(queue.get(left).getCost() < pivot.getCost()){
                left++;
            }
            while (queue.get(right).getCost() > pivot.getCost()){
                right--;
            }
            if (left < right){
                tmp = queue.get(left);

                queue.set(left, queue.get(right));
                queue.set(right, tmp);
                left++;
                right--;

            }else{
                if(left == right){
                    left++;
                    right--;
                }

            }
        }
        num a = new num();
        a.setLeft(left);
        a.setRight(right);

        return a;

    }

    public boolean Vacio(){

        return queue.isEmpty();
    }
}
