package br.com.monitor.controllers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jezhumble.javasysmon.MemoryStats;

import br.com.monitor.models.Cpu;
import br.com.monitor.services.CpuSerivces;

@RestController
@RequestMapping("/cpu")
public class CpuController {

	@GetMapping(path = "/listprocess")
	public Cpu process() throws IOException {
		CpuSerivces cpuSerivces = new CpuSerivces();
		Cpu processArray = cpuSerivces.getWindowsProcess();
		return processArray;
	}
	
	@GetMapping(path = "/osname")
	public String getOsName(){
		CpuSerivces cpuSerivces = new CpuSerivces();
		String osName = cpuSerivces.getOsName();
		return osName;
	}
	
	@DeleteMapping(path = "/killprocess/{pid}")
	public void killProcesss(@PathVariable("pid") int pid){
		CpuSerivces cpuSerivces = new CpuSerivces();
		cpuSerivces.killProcess(pid);
	}
	
	@GetMapping(path = "/cpuuptime")
	public String getUptimeInSeconds(){
		CpuSerivces cpuSerivces = new CpuSerivces();
		long uptimeInSeconds = cpuSerivces.getUptimeInSeconds();
		return TimeUnit.SECONDS.toHours(uptimeInSeconds) + " Hours";
	}
	
	@GetMapping(path = "/memorystats")
	public MemoryStats getMemoryStats(){
		CpuSerivces cpuSerivces = new CpuSerivces();
		MemoryStats memory = cpuSerivces.getPhysicalMemory();
		return memory;
	}
	
	@GetMapping(path = "/numcpus")
	public String getNumCpus(){
		CpuSerivces cpuSerivces = new CpuSerivces();
		int cpus = cpuSerivces.getNumCpus();
		return cpus + " Cpus";
	}
	

}
