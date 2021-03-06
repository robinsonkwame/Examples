package com.mzlabs.count.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.mzlabs.count.op.iter.SeqLE;
import com.mzlabs.count.op.iter.SeqLT;

public class TestIntVec {

	
	@Test
	public void testContents() {
		final int[] a = new int[] {1, 2, 3};
		final IntVec v = new IntVec(a);
		assertEquals(a.length,v.dim());
		for(int i=0;i<a.length;++i) {
			assertEquals(a[i],v.get(i));
		}
	}
	
	@Test
	public void testComp() {
		final IntVec iv0 = new IntVec(new int[] {0,0,0});
		final IntVec ivnz = new IntVec(new int[] {0,1,0});
		final Set<IntVec> s = new HashSet<IntVec>();
		for(final IntVec v : new IntVec[] {iv0, ivnz}) {
			assertTrue(v.compareTo(v)==0);
			assertTrue(v.equals(v));
			s.add(v);
		}
		assertEquals(2,s.size());
		assertTrue(iv0.compareTo(ivnz)!=0);
		assertFalse(iv0.equals(ivnz));
		assertFalse(iv0.toString().compareTo(ivnz.toString())==0);
	}
	
	@Test
	public void testAdvance() {
		final SeqLT seq = new SeqLT(3,4);
		final int[] x = seq.first();
		int n = 0;
		do {
			for(int i=0;i<x.length;++i) {
				assertTrue((x[i]>=0)&&(x[i]<4));
			}
			++n;
		} while(seq.advance(x));
		assertEquals(64,n);
	}
	
	@Test
	public void testAdvanceV() {
		final int[] b = new int[5];
		for(int i=0;i<3;++i) {
			b[i+1] = i+1;
		}
		final IntVec bv = new IntVec(b);
		final SeqLE seq = new SeqLE(bv);
		final int[] x = new int[5];
		int n = 0;
		do {
			for(int i=0;i<x.length;++i) {
				assertTrue((x[i]>=0)&&(x[i]<=bv.get(i)));
			}
			++n;
		} while(seq.advance(x));
		assertEquals(24,n);
	}
}
