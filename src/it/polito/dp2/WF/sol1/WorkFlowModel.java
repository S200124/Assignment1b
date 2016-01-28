package it.polito.dp2.WF.sol1;

import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public final class WorkFlowModel {
	
	private WorkFlowModel() {}
	
	private static Node accessFile()
	{
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
	        File xmlFile = new File(System.getProperty("it.polito.dp2.WF.sol1.WFInfo.file"));
	        Document dom = builder.parse(xmlFile);
	        if(dom.hasChildNodes())
	        	return dom;
		}
		catch (Exception ex)
		{
			return (Node) ex;
	    }
		return null;
	}
	
	private static List<Node> getNodesByType(String type)
	{
		List<Node> ret = new ArrayList<Node>();
		Node root = accessFile();
		
		if(root != null)
		{
			NodeList nlChilds = root.getChildNodes();
            
            for (int iChild = 0; iChild < nlChilds.getLength(); iChild++)
            {
            	Node currentNode = nlChilds.item(iChild);
            	if (currentNode.getNodeType() == Node.ELEMENT_NODE)
            		if (currentNode.getNodeName() == type)
            			ret.add(currentNode);
            }
		}

		return ret;
	}
	
	public static List<Node> allWorkflow()
	{
		return getNodesByType("workflow");
	}
	
	public static List<Node> allProcesses()
	{
		return getNodesByType("process");
	}
	
	public static List<Node> whereProcesses(String workflowName)
	{
		List<Node> ret = new ArrayList<Node>();
		
		for(Node currentNode:allProcesses())
		{
			NamedNodeMap nnm = currentNode.getAttributes();
			if (nnm != null && nnm.getLength() > 0)
				for (int iAttr=0; iAttr < nnm.getLength(); iAttr++)
	            	   if(nnm.item(iAttr).getNodeName() == "workflowName" && nnm.item(iAttr).getNodeValue() == workflowName)
	            		   ret.add(currentNode);
		}
		
		return ret;
	}
	
	public static Node findWorkflow(String name)
	{	
		for(Node currentNode:allWorkflow())
		{
			NamedNodeMap nnm = currentNode.getAttributes();
			if (nnm != null && nnm.getLength() > 0)
				for (int iAttr=0; iAttr < nnm.getLength(); iAttr++)
	            	   if(nnm.item(iAttr).getNodeName() == "name" && nnm.item(iAttr).getNodeValue() == name)
	            		   return currentNode;
		}
		
		return null;
	}
	
	public static HashMap<String,String> getAttibutes(Node currentNode)
	{
		HashMap<String,String> ret = new HashMap<String,String>();
		NamedNodeMap nnm = currentNode.getAttributes();
		
		if (nnm != null && nnm.getLength() > 0)
		{
	        for (int iAttr=0; iAttr < nnm.getLength(); iAttr++)
	        	ret.put(nnm.item(iAttr).getNodeName(), nnm.item(iAttr).getNodeValue());
		}
		
		return ret;
	}
}
