package com.study.pattern04.stretegy3;

public class Informix extends Database {
	public Informix() {
		name = "Informix";
		rows = 20;
	}
	@Override
	public void connectDatabase() {
		System.out.println(name + "에 접속했습니다.");
	}


}
