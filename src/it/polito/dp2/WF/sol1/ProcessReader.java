package it.polito.dp2.WF.sol1;

import java.text.*;
import java.util.*;
import org.w3c.dom.*;

public class ProcessReader implements it.polito.dp2.WF.ProcessReader {
	
	private Node process;
	private String workflowName;
	
	public ProcessReader(Node pr, String wfn)
	{
		process = pr;
		workflowName = wfn;
	}
	
	@Override
	public Calendar getStartTime() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(process);
		try
		{
			DateFormat df = new SimpleDateFormat("D M j G:i:s T Y");
			Calendar cal  = Calendar.getInstance();
			cal.setTime(df.parse(attr.get("startAt")));
			return cal;
		}
		catch (ParseException e)
		{
			return null;
		}	
	}

	@Override
	public List<it.polito.dp2.WF.ActionStatusReader> getStatus() {
		List<it.polito.dp2.WF.ActionStatusReader> ret = new ArrayList<it.polito.dp2.WF.ActionStatusReader>();
		
		for(Node currentNode:WorkFlowModel.allActionStatus(process))
			ret.add(new ActionStatusReader(currentNode));
		
		return ret;
	}

	@Override
	public WorkflowReader getWorkflow() {
		return (new WorkflowReader(WorkFlowModel.findWorkflow(workflowName)));
	}

}
