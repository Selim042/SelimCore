package selim.machines;

public interface IEnergyStorage {

	int removeEnergy(int e, boolean simulate);

	int addEnergy(int e, boolean simulate);

	int getEnergy();

	int getMaxEnergy();

	boolean canRemoveEnergy();

	boolean canReceiveEnergy();

	int maxRemove();

	int maxAdd();

}
