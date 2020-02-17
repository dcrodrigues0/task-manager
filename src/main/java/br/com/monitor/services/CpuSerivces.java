package br.com.monitor.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jezhumble.javasysmon.JavaSysMon;
import com.jezhumble.javasysmon.MemoryStats;

import br.com.monitor.models.Cpu;

@Service
public class CpuSerivces {
	
	JavaSysMon javaSysMon = new JavaSysMon();
	
	public String getOsName() {
		String osName = javaSysMon.osName();
		return osName;
	}
	
	public long getUptimeInSeconds() {
		long uptimeInSeconds = javaSysMon.uptimeInSeconds();
		return uptimeInSeconds;
	}
	
	public void killProcess(int processId) {
		javaSysMon.killProcess(processId);
	}
	
	public MemoryStats getPhysicalMemory() {
		MemoryStats memory = javaSysMon.physical();
		return memory;
	}
	
	public int getNumCpus() {
		int numCpus = javaSysMon.numCpus();
		return numCpus;
	}
	
	
	public synchronized static String execCommand(final String commandLine) throws IOException {
		boolean success = false;
		String result;
		
		Process p;
		BufferedReader input;
		StringBuffer cmdOut = new StringBuffer();
		String lineOut = null;
		int numberOfOutline = 0;

		try {
			p = Runtime.getRuntime().exec(commandLine);

			input = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((lineOut = input.readLine()) != null) {
				if (numberOfOutline > 0) {
					cmdOut.append("\n");
				}
				cmdOut.append(lineOut);
				numberOfOutline++;
			}

			result = cmdOut.toString();

			success = true;

			input.close();
			
		} catch (IOException e) {
			result = String.format("Falha ao executar comando %s. Erro: %s", commandLine, e.toString());
		}

		// Se não executou com sucesso, lança a falha
		if (!success) {
			throw new IOException(result);
		}

		return result;
	}
	
	
	public Cpu getWindowsProcess() throws IOException {
		
		//LISTANDO PROCESSOS DO WINDOWS VIA CMD
		CpuSerivces cpuSerivces = new CpuSerivces();
		String process = cpuSerivces.execCommand("tasklist");
		Cpu cpu = new Cpu();
		Map<String,String> map = new HashMap<String,String>();
		
		//PEGANDO PROCESSO A PROCESSO E ARMAZENANDO EM UMA ARRAYLIST
		String[] linhas = process.split("\\r\\n|\\r|\\n", -1);
		ArrayList<String> processPid = new ArrayList<String>();
		for (int i = 3; i < linhas.length; i++) {
			//IGNORANDO AS 3 PRIMEIRAS LINHAS DO TASKLIST
			processPid.add(0,"");
			processPid.add(1,"");
			processPid.add(2,"");
			processPid.add(i,linhas[i].substring(28,35)); 
			map.put(linhas[i].substring(28,35).trim(), linhas[i].trim());
		}
		cpu.setProcess(map);
		return cpu;
		
	}
	
}
