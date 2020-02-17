package br.com.monitor.models;

import java.util.ArrayList;
import java.util.Map;

public class Cpu {

	private Map<String, String> process;

	public Map<String, String> getProcess() {
		return process;
	}

	public void setProcess(Map<String, String> process) {
		this.process = process;
	}
}
