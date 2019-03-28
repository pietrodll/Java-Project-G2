package ui.clui;

import bike.ElectricBike;
import card.Card;
import station.Slot;
import station.Station;
import user.User;

public class CommandLineDisplay {
	
	public String display(Station s) {
		String disp = "";
		disp += "Station: id:" + s.getId() + '\n';
		float x = (float) (Math.round(s.getP().getX()*1000)/1000.0);
		float y = (float) (Math.round(s.getP().getY()*1000)/1000.0);
		disp += '\t' + "Position: x=" + x + " y=" + y + '\n';
		disp += "Slots: " + s.getParkingSlots().size() + '\n';
		for (Slot slot : s.getParkingSlots()) {
			disp += '\t' + "id:" + slot.getId() + '\t' + (slot.isOnline() ? "Online" : "Offline") +'\t' + (slot.getisOccupied() ? "Occupied" : "Free");
			if (slot.getisOccupied()) {
				disp += '\t' + "Bike id:" + slot.getBike().getId();
				String bt = slot.getBike() instanceof ElectricBike ? "Electric" : "Mechanic";
				disp += '\t' + "Type: " + bt;
			}
			disp += '\n';
		}
		System.out.println(disp);
		return disp;
	}
	
	public String display(Card c) {
		String disp = "";
		disp += "Card: id:" + c.getId() + '\n';
		disp += '\t' + "Owner: " + c.getUser().getUserName() + '\n';
		disp += '\t' + "Type: " + c.getTypeString() + '\n';
		disp += '\t' + "Credit: " + c.getTimeCredit() + " minutes" + '\n';
		System.out.println(disp);
		return disp;
	}
	
	public String display(User u) {
		double credit = Math.round(u.getUserStat().getTotalAmount()*100)/100.0;
		String disp = "";
		disp += "User: id:" + u.getId() + '\n';
		disp += '\t' + "Name: " + u.getUserName() + '\n';
		disp += '\t' + "Total amount paid: " + credit + " euros" + '\n';
		disp += '\t' + "Total number of rides: " + u.getUserStat().getNumberRides() + '\n';
		disp += '\t' + "Total ride time: " + Math.round(u.getUserStat().getTotalTime()) + " minutes" + '\n';
		disp += '\t' + "Total credit earned: " + Math.round(u.getUserStat().getTotalCreditEarned()) + " minutes" + '\n';
		System.out.println(disp);
		return disp;
	}
	
	public void display(String s) {
		System.out.println(s);
	}
	
}
