package test;

import openihm.api.system.Ostream;
import openihm.api.system.System;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;
import openihm.api.utils.String;

class BasicListTest {
	
	
	private final List<Integer> list;
	
	private final Ostream cout = System.cout;
	private final Ostream cerr = System.cerr;
	
	private static String f(final java.lang.String str) { return String.$(str); }
	
	public BasicListTest(final List<Integer> list) {
		this.list = list;
	}
	
	private boolean clear() {
		list.resize(0);
		if(size(0)) {
			cout.$(f("clear work")).endl();
			return true;
		}
		cerr.$(f("size of clear")).endl();
		return false;
	}
	
	
	private boolean add() {
		list.add(16);
		list.add(12);
		list.add(4);
		list.add(874);
		list.add(55);
		if(size(5)) {
			cout.$(f("add work")).endl();
			return true;
		}
		cerr.$(f("size of add")).endl();
		return false;
	}
	
	private boolean get() {
		if(list.get(0) != 16) cerr.$(f("index 0 not equal 16 : ")).$(list.get(0)).endl();
		else if(list.get(1) != 12) cerr.$(f("index 0 not equal 12 : ")).$(list.get(1)).endl();
		else if(list.get(2) != 4) cerr.$(f("index 0 not equal 4 : ")).$(list.get(2)).endl();
		else if(list.get(3) != 874)cerr.$(f("index 0 not equal 874 : ")).$(list.get(3)).endl();
		else if(list.get(4) != 55) cerr.$(f("index 0 not equal 55 : ")).$(list.get(4)).endl();
		else {
			cout.$(f("get work")).endl();
			return true;
		}
		return false;
	}
	
	private boolean remove() {
		list.remove(3);
		if(!size(4)) {
			cerr.$(f("size of remove beetween two case")).endl();
			return false;
		}
		if(list.get(3) != 55) {
			cerr.$(f("error remove beetween two case")).endl();
			cerr.$(f("normal value : ")).$(55).endl();
			cerr.$(f("actual value : ")).$(list.get(3)).endl();
			return false;
		}
		list.remove(3);
		if(!size(3)) {
			cerr.$(f("size of remove last case")).endl();
			return false;
		}
		if(list.get(2) != 4) {
			cerr.$(f("error remove last case")).endl();
			cerr.$(f("normal value : ")).$(4).endl();
			cerr.$(f("actual value : ")).$(list.get(2)).endl();
			return false;
		}
		list.remove(0);
		if(!size(2)) {
			cerr.$(f("size of remove first case")).endl();
			return false;
		}
		if(list.get(0) != 12) {
			cerr.$(f("error remove first case")).endl();
			cerr.$(f("normal value : ")).$(12).endl();
			cerr.$(f("actual value : ")).$(list.get(0)).endl();
			return false;
		}
		list.remove(1);
		list.remove(0);
		list.add(15);
		list.add(16);
		list.remove(0);
		list.remove(0);
		cout.$(f("remove work")).endl();
		return true;
	}
	
	private boolean set() {
		list.set(0, 20);
		list.set(2, 22);
		list.set(4, 24);
		if(list.get(0) != 20) {
			cerr.$(f("error set first case")).endl();
			return false;
		}
		else if(list.get(2) != 22) {
			cerr.$(f("error set between two case")).endl();
			return false;
		}
		else if(list.get(4) != 24) {
			cerr.$(f("error set last case")).endl();
			return false;
		}
		cout.$(f("set work")).endl();
		return true;
	}
	
	private boolean insert() {
		list.insert(4, 230);
		if(!size(6)) {
			cerr.$(f("size of insert last case")).endl();
			return false;
		}
		if(list.get(4) != 230) {
			cerr.$(f("error insert last case")).endl();
			cerr.$(f("normal value : ")).$(230).endl();
			cerr.$(f("actual value : ")).$(list.get(4)).endl();
			return false;
		}
		list.insert(3, 282);
		if(!size(7)) {
			cerr.$(f("size of insert beetween two case")).endl();
			return false;
		}
		if(list.get(3) != 282) {
			cerr.$(f("error insert beetween two case")).endl();
			cerr.$(f("normal value : ")).$(282).endl();
			cerr.$(f("actual value : ")).$(list.get(3)).endl();
			return false;
		}
		list.insert(0, 952);
		if(!size(8)) {
			cerr.$(f("size of insert first case")).endl();
			return false;
		}
		if(list.get(0) != 952) {
			cerr.$(f("error insert first case")).endl();
			cerr.$(f("normal value : ")).$(952).endl();
			cerr.$(f("actual value : ")).$(list.get(0)).endl();
			return false;
		}
		cout.$(f("insert work")).endl();
		return true;
	}
	
	private boolean resize() {
		list.resize(3);
		if(!size(3)) {
			cerr.$(f("size of resize")).endl();
			return false;
		}
		if(!goodValue(0, 10, f("error resize1 case 0"))) { return false; }
		else if(!goodValue(1, 11, f("error resize1 case 1"))) { return false; }
		else if(!goodValue(2, 12, f("error resize1 case 2"))) { return false; }
		list.resize(5);
		if(!size(5)) {
			cerr.$(f("size of resize")).endl();
			return false;
		}
		if(!goodValue(0, 10, f("error resize2 case 0"))) { return false; }
		else if(!goodValue(1, 11, f("error resize2 case 1"))) { return false; }
		else if(!goodValue(2, 12, f("error resize2 case 2"))) { return false; }
		else if(!goodValue(3, null, f("error resize2 case 3"))) { return false; }
		else if(!goodValue(4, null, f("error resize2 case 4"))) { return false; }
		cout.$(f("resize work")).endl();
		return true;
	}
	
	private boolean iterator() {
		final Iterator<Integer> it = list.iterator();
		if(it.get() != list.get(0)) {
			cerr.$(f("error iterator get")).endl();
			cerr.$(f("normal value : ")).$(list.get(0)).endl();
			cerr.$(f("actual value : ")).$(it.get()).endl();
			return false;
		}
		it.set(1000);
		if(it.get() != 1000) {
			cerr.$(f("error iterator set")).endl();
			cerr.$(f("normal value : ")).$(1000).endl();
			cerr.$(f("actual value : ")).$(it.get()).endl();
			return false;
		}
		it.next();
		for(int i = 1; i < list.size() ; i++) {
			if(it.get() != list.get(i)) {
				cerr.$(f("error iterator next")).endl();
				cerr.$(f("normal value : ")).$(list.get(i)).endl();
				cerr.$(f("actual value : ")).$(it.get()).endl();
				return false;
			}
			it.next();
		}
		if(!it.isEnd()) {
			cerr.$(f("error iterator isEnd")).endl();
			return false;
		}
		cout.$(f("iterator work")).endl();
		return true;
	}
	
	private boolean addFirst() {
		list.addFirst(359);
		list.addFirst(358);
		list.addFirst(357);
		if(!size(3)) {
			cerr.$(f("size of addFirst")).endl();
			return false;
		}
		if(!goodValue(0, 357, f("error addfirst case 0"))) return false;
		if(!goodValue(1, 358, f("error addfirst case 1"))) return false;
		if(!goodValue(2, 359, f("error addfirst case 2"))) return false;
		cout.$(f("addFirst work")).endl();
		return true;
	}
	
	private boolean removeLast() {
		list.removeLast();
		if(!size(2)) {
			cerr.$(f("size of removeLast")).endl();
			return false;
		}
		if(!goodValue(1, 761, f("error addfirst case 1"))) return false;
		list.removeLast();
		list.removeLast();
		if(!size(0)) {
			cerr.$(f("size of removeLast")).endl();
			return false;
		}
		cout.$(f("removeLast work")).endl();
		return true;
	}
	
	private boolean removeFirst() {
		list.removeFirst();
		if(!size(2)) {
			cerr.$(f("size of removeFirst")).endl();
			return false;
		}
		if(!goodValue(0, 358, f("error removefirst"))) return false;
		list.removeFirst();
		list.removeFirst();
		if(!size(0)) {
			cerr.$(f("size of removeFirst")).endl();
			return false;
		}
		cout.$(f("removeFirst work")).endl();
		return true;
	}
	
	
	private boolean goodValue(final int ind, final Integer normal, final String str) {
		if(list.get(ind) != normal) {
			cerr.$(str).endl();
			cerr.$(f("normal value : ")).$(normal).endl();
			cerr.$(f("actual value : ")).$(list.get(ind)).endl();
			return false;
		}
		return true;
	}
	
	private boolean goodValue(final int ind, final int normal, final String str) {
		if(list.get(ind) != normal) {
			cerr.$(str).endl();
			cerr.$(f("normal value : ")).$(normal).endl();
			cerr.$(f("actual value : ")).$(list.get(ind)).endl();
			return false;
		}
		return true;
	}
	
	private boolean size(final int size) {
		if(list.size() == size) return true;
		cerr.$(f("error size : ")).$(list.size()).endl().$(f("normal size ")).$(size).endl();
		return false;
	}
	
	boolean test() {
		if(!clear()) return false;
		if(!add()) return false;
		if(!get()) return false;
		if(!remove()) return false;
		list.resize(0);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		if(!set()) return false;
		if(!insert()) return false;
		list.resize(0);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		if(!resize()) return false;
		if(!iterator()) return false;
		list.resize(0);
		if(!addFirst()) return false;
		if(!removeFirst()) return false;
		list.add(760);
		list.add(761);
		list.add(762);
		if(!removeLast()) return false;
		return true;
	}

}
