package Distribucion_Carga.Cola2;

import Calculo_Disponibilidad.Cola.num;
import Calculo_Disponibilidad.Solution;
import Distribucion_Carga.Solution2;
import users.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cola2 {
    ArrayList<ArrayList<Integer>> queue = new ArrayList<>();

    public Cola2(){

    }

    public void enqueueNoOrdenate(Integer a){
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(a);
        queue.add(aux);
    }

    public void Ordenate(){


        QuickSort2(0, queue.size() - 1);
    }

    public void enqueue(ArrayList<Integer> a){
        queue.add(a);
        QuickSort2(0, queue.size() - 1);
    }

    public ArrayList<Integer> dequeue(){

        ArrayList<Integer> a = queue.get(0);
        queue.remove(0);
        return a;
    }

    private void QuickSort2(int start, int length){

        if(start < length){
            num a = Particio(start, length);
            QuickSort2(start, a.getRight());
            QuickSort2(a.getLeft(), length);
        }

    }

    private num Particio(int start, int length){
        int mig = (start + length)/2;
        ArrayList<Integer> pivot = queue.get(mig);
        ArrayList<Integer> tmp;
        int left = start;
        int right = length;


        while (left <= right){
            while(queue.get(left).size() > pivot.size()){
                left++;
            }
            while (queue.get(right).size() < pivot.size()){
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
