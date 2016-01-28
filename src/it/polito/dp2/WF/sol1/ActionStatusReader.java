package it.polito.dp2.WF.sol1;

import it.polito.dp2.WF.Actor;
import java.util.Calendar;
import org.w3c.dom.Node;

public class ActionStatusReader implements it.polito.dp2.WF.ActionStatusReader {

	private Node actionStat;
	
	public ActionStatusReader(Node as)
	{
		actionStat = as;
	}
	
	@Override
	public String getActionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor getActor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calendar getTerminationTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTakenInCharge() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

}
