package selim.machines;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;

public abstract class EnergyMachine extends Machine implements ITickable, IEnergyStorage {

	private int energy;
	private int maxEnergy;

	public EnergyMachine(String id) {
		super(id);
	}

	@Override
	public NbtCompound writeToNbt(NbtCompound nbt) {
		super.writeToNbt(nbt);
		nbt.put("energy", this.energy);
		return nbt;
	}

	@Override
	public void readFromNbt(NbtCompound nbt) {
		if (nbt.containsKey("energy"))
			this.energy = nbt.getInteger("energy");
	}

	@Override
	public int getEnergy() {
		return this.energy;
	}

	protected void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	@Override
	public int getMaxEnergy() {
		return this.maxEnergy;
	}

	@Override
	public int removeEnergy(int e, boolean simulate) {
		if (e <= 0)
			return 0;
		if (e > this.maxRemove()) {
			if (!simulate)
				this.energy -= this.maxRemove();
			return this.maxRemove();
		} else if (this.energy - e < 0) {
			this.energy = 0;
			if (!simulate)
				return this.energy;
		}
		if (!simulate)
			this.energy -= e;
		return e;
	}

	@Override
	public int addEnergy(int e, boolean simulate) {
		if (e <= 0)
			return 0;
		if (e > this.maxAdd()) {
			this.energy += this.maxAdd();
			if (!simulate)
				return this.maxAdd();
		} else if (this.energy + e > maxEnergy) {
			if (!simulate)
				this.energy = maxEnergy;
			return maxEnergy - e;
		}
		if (!simulate)
			this.energy += e;
		return e;
	}

	@Override
	public boolean canRemoveEnergy() {
		return true;
	}

	@Override
	public boolean canReceiveEnergy() {
		return true;
	}

}
