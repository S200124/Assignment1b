package it.polito.dp2.WF.sol1;

import it.polito.dp2.WF.WorkflowMonitorException;
import java.io.*;

import javax.xml.parsers.*;

public class WorkflowMonitorFactory extends it.polito.dp2.WF.WorkflowMonitorFactory {
	private String fileName;
	
	public WorkflowMonitorFactory()
	{
		fileName = System.getProperty("it.polito.dp2.WF.sol1.WFInfo.file");
	}
	
	@Override
	public WorkflowMonitor newWorkflowMonitor() throws WorkflowMonitorException {
		WorkflowMonitor ret;
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
	        File xmlFile = new File(fileName);
	        ret = new WorkflowMonitor(builder.parse(xmlFile));
		}
		catch (Exception ex)
		{
			ret = null;
	    }
		
		return ret;
	}

}
