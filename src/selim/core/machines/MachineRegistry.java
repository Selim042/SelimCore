package selim.core.machines;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import selim.core.SelimCore;

public class MachineRegistry {

	private static final List<Machine> MACHINES = new ArrayList<Machine>();

	public void registerMachine(Class<? extends Machine> machine) throws DuplicateIDException {
		Machine m;
		try {
			m = machine.newInstance();
			for (Machine rM : MACHINES) {
				if (rM.getID().equals(m.getID())) {
					throw new DuplicateIDException();
				}
			}
			MACHINES.add(m);
		} catch (InstantiationException | IllegalAccessException e) {
			SelimCore.getCoreLogger().log(Level.SEVERE,
					"Machine classes must contain a public default constructor.", e);
		}
	}

	public static Class<? extends Machine> getMachineByID(String id) {
		for (Machine m : MACHINES)
			if (m.getID().equals(id))
				return m.getClass();
		return null;
	}

}
