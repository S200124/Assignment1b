package it.polito.dp2.WF.sol1;

import java.text.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.w3c.dom.*;

public class ProcessReader implements it.polito.dp2.WF.ProcessReader {
	
	private Node process;
	
	public ProcessReader(Node pr)
	{
		process = pr;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkflowReader getWorkflow() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(process);
		String workflowName = attr.get("workflowName");
		
		return (new WorkflowReader(WorkFlowModel.findWorkflow(workflowName)));
	}

}
