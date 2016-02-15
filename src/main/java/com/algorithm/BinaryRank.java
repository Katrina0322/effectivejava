package com.algorithm;

public class BinaryRank {
	public static int rank(int k,int[] a){
		return rank(k, a, 0, a.length-1);
	}
	
	
	public static int rank(int k,int[] a,int lo,int hi){
		if(lo>hi) return -1;
		int mid=lo + (hi-lo)/2;
		if(k<a[mid]){
			return rank(k, a,lo,mid-1);
		}else if(k>a[mid]){
			return rank(k, a,mid+1,hi);
		}else{
			return a[mid];
		}
	}
}
