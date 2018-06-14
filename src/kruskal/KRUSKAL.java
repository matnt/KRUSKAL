/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal;

import java.util.Scanner;

/**
 *
 * @author Mat Nguyen
 */
public class KRUSKAL {
    static final int MAX =1000;
    int n; // so dinh
    int m; // so canh
    int[] p; // cha
    int[] rank; // muc
    int[] u;
    int[] v;
    int[] c;
    int count;
    int W;

    public KRUSKAL(){
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KRUSKAL kruskal = new KRUSKAL();
        kruskal.input();
        kruskal.solve();
        kruskal.printResult();
    }
    
    public void input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        u = new int[m];
        v = new int[m];
        c = new int[m];
        rank = new int[n + 1];
        p = new int[n + 1];
        count = 0;
        W = 0;
        for(int i = 0; i < m; i++){
            u[i] = in.nextInt();
            v[i] = in.nextInt();
            c[i] = in.nextInt();
        }
    }
    // lien ket 2 cay voi nhau
    public void link(int x, int y){
        if(rank[x] > rank[y])
            p[y] = x;
        else {
            p[x] = y;
            if(rank[x] == rank[y])
                rank[y] = rank[y] + 1;
        }
    }
    
    public void makeSet(int x){
        p[x] = x;
        rank[x] = 0;
    }
    
    public int findset(int x){
        if(x != p[x])
            p[x] = findset(p[x]);
        return p[x];
    }

    private void quicksort( int l, int r) {
        if(l < r){
            int index = (l + r)/2;
            index = partition(l, r, index);
            if(l < index) quicksort(l, index - 1);
            if(index < r) quicksort(index + 1, r);
        }
    }
    
    private int partition(int l, int r, int index){
        int pivot = c[index];
        swap(index, r);
        int storeIndex = l;
        for(int i = l; i <= r - 1; i++){
            if(c[i] < pivot){
                swap(storeIndex, i);
                storeIndex++;
            }
        }
        swap(storeIndex, r);
        return storeIndex;
    }
    
    public void swap(int a, int b) {
        int tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
        
        int tmp1 = u[a];
        u[a] = u[b];
        u[b] = tmp1;
        
        int tmp2 = v[a];
        v[a] = v[b];
        v[b] = tmp2;
    }
    
    private void solve(){
        for(int i = 1; i <= n; i++){
            makeSet(i);
        }
        quicksort(0, m - 1);

        for(int i = 0; i < m; i++){
            int ru = findset(u[i]);
            int rv = findset(v[i]);
            if(ru != rv){
                link(ru, rv);
                W += c[i];
                count++;
                if(count == n - 1)
                    break;
            }
        }
    }
    
    private void printResult(){
        System.out.println(W + " ");

    }
    
}
