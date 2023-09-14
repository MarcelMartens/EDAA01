package lpt;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	private Machine[] machines;

	/**
	 * Skapar en schemaläggare för maskinerna
	 * i vektorn machines.
	 */

	// fix tog bort Machine[] konstruktor och lade till this.
	public Scheduler(Machine[] machineArray) {
		this.machines = machineArray;
	}

	/* Returnerar den maskin som har minst att göra. */
	private Machine machineWithLeastToDo() {
		int min = Integer.MAX_VALUE;
		int minPos = -1;
		for (int i = 0; i < machines.length; i++) {
			int totalTime = machines[i].getScheduledTime();
			if (totalTime < min) {
				min = totalTime;
				minPos = i;
			}
		}
		return machines[minPos];
	}

	/** Fördelar jobben i listan jobs på maskinerna. */
	// fix ändrade plats på j1 och j2 så att ordningen blir rätt.
	public void makeSchedule(List<Job> jobs) {
		List<Job> tempJobList = new ArrayList<>(jobs);
		tempJobList.sort((j1, j2) -> j2.getTime() - j1.getTime());
		for (Job j : tempJobList) {
			Machine m = machineWithLeastToDo();
			m.assignJob(j);
		}
	}

	/** Tar bort alla jobb från maskinerna. */
	public void clearSchedule() {
		for (int i = 0; i < machines.length; i++) {
			machines[i].clearJobs();
		}
	}

	/** Skriver ut maskinernas scheman. */
	// fix ändrade <= till < så att den ej letar efter index högre än 3
	public void printSchedule() {
		for (int i = 0; i < machines.length; i++) {
			System.out.println(machines[i]);
		}
	}
}
