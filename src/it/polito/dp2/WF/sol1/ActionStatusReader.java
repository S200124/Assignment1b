package it.polito.dp2.WF.sol1;

import it.polito.dp2.WF.Actor;

import java.util.*;

import org.w3c.dom.Node;

public class ActionStatusReader implements it.polito.dp2.WF.ActionStatusReader {

	private Node actionStat;
	
	public ActionStatusReader(Node as)
	{
		actionStat = as;
	}
	
	@Override
	public String getActionName() {
		String actionName = WorkFlowModel.getNodeValue(WorkFlowModel.getActionName(actionStat));
		if(actionName != null)
			return actionName;
		else
			return "";
	}

	@Override
	public Actor getActor() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(WorkFlowModel.getActor(actionStat));
		String role = WorkFlowModel.getNodeValue(WorkFlowModel.getRole(WorkFlowModel.getActor(actionStat)));

		if(role != null && attr.containsKey("name"))
		{
			Actor act = new Actor(attr.get("name"),role);
			return act;
		}
		else
			return null;
	}

	@Override
	public Calendar getTerminationTime() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(actionStat);
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		//dateFormat df = new dateFormat("dd/MM/yyyy hh:mm");
		Calendar cal  = Calendar.getInstance();
		cal.setTimeInMillis(Long.parseLong(attr.get("terminatedAt")));
		//cal.setTime(dateFormat.parse());
		return cal;
	}

	@Override
	public boolean isTakenInCharge() {
		if(getActor() != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean isTerminated() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(actionStat);
		if(attr.containsKey("terminatedAt"))
			return true;
		else
			return false;
	}

}
