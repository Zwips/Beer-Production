package communication.MailCommunication;

import communication.IMailCommunicationFacade;

public class MailCommunicationFacade implements IMailCommunicationFacade {


	@Override
	public void SendInventoryEmail(String machineName) {
		new InventoryEmail().SendInventoryEMail(machineName);
	}

	@Override
	public void SendAbortEmail(String machineName) {
		new AbortEmail().SendAbortEmail(machineName);
	}

	@Override
	public void SendMaintenanceEmail(String machineName) {
		new MaintenanceEmail().SendMaintenenceEMail(machineName);
	}

	@Override
	public void SendPowerLossEmail(String machineName) {
		new PowerLossEmail().SendPowerLossEmail(machineName);
	}

	@Override
	public void SendStopEmail(String machineName) {
		new StopEmail().SendStopEMail(machineName);
	}
}
