package Distribucion_Carga.Cola2;

import Calculo_Disponibilidad.Cola.num;
import Calculo_Disponibilidad.Solution;
import Distribucion_Carga.Solution2;
import users.User;

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

    public void enqueue(Solution2 a){
        queue.add(a);
        QuickSort2(0, queue.size() - 1);
    }

    public Solution2 dequeue(){

        Solution2 a = queue.get(0);
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
        Solution2 pivot = queue.get(mig);
        Solution2 tmp;
        int left = start;
        int right = length;


        while (left <= right){
            while(queue.get(left).getActivity() > pivot.getActivity()){
                left++;
            }
            while (queue.get(right).getActivity() < pivot.getActivity()){
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
