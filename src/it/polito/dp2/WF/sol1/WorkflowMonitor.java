package it.polito.dp2.WF.sol1;

import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowReader;

import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WorkflowMonitor implements it.polito.dp2.WF.WorkflowMonitor {
	private Document wfInfo;
	
	private Set<Object> searchNodes(String type)
	{
		Set<Object> ret = new HashSet<Object>();
	
		if (wfInfo.hasChildNodes())
		{
            NodeList nlChilds = wfInfo.getChildNodes();
            int iChildNumber = nlChilds.getLength();
            for (int iChild = 0; iChild < iChildNumber; iChild++)
            {
            	Node currentNode = nlChilds.item(iChild);
            	if (currentNode.getNodeType() == Node.ELEMENT_NODE)
            		if (currentNode.getNodeName() == type)
            		{
            			ret.add(currentNode);
            		}
            }
		}
		
		if(ret.isEmpty())
			return null;
		else
			return ret;
	}

	public WorkflowMonitor(Document dom)
	{
		wfInfo = dom;
	}
	
	@Override
	public Set<ProcessReader> getProcesses() {
		Set<ProcessReader> ret;
		
		return ret;
	}

	@Override
	public WorkflowReader getWorkflow(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<WorkflowReader> getWorkflows() {
		Set<WorkflowReader> ret;
		
		return ret;
	}

}
