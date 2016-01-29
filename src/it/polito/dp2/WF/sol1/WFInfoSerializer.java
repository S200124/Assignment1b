package it.polito.dp2.WF.sol1;

import it.polito.dp2.WF.ActionReader;
import it.polito.dp2.WF.ActionStatusReader;
import it.polito.dp2.WF.ProcessActionReader;
import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.SimpleActionReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.WorkflowMonitorFactory;
import it.polito.dp2.WF.WorkflowReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.List;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;


public class WFInfoSerializer {
	private WorkflowMonitor monitor;
	private DateFormat dateFormat;

	
	/**
	 * Default constructor
	 * @throws WorkflowMonitorException 
	 */
	public WFInfoSerializer() throws WorkflowMonitorException {
		WorkflowMonitorFactory factory = WorkflowMonitorFactory.newInstance();
		monitor = factory.newWorkflowMonitor();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	}
	
	public WFInfoSerializer(WorkflowMonitor monitor) {
		super();
		this.monitor = monitor;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WFInfoSerializer wf;
		try {
			wf = new WFInfoSerializer();
			//wf.printAll();
			wf.createXmlFile(args[0]);
		} catch (WorkflowMonitorException e) {
			System.err.println("Could not instantiate data generator.");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void createXmlFile(String fileName)
	{
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			DocumentType doctype = doc.getImplementation().createDocumentType("wfInfo", "", "wfInfo.dtd");
			
			// root element
			Element rootElement = doc.createElement("wfInfo");
			doc.appendChild(rootElement);
			
			if(appendData(doc, rootElement))
			{

			// write the content into XML file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/it/polito/dp2/WF/sol1/" + fileName));
	
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);
	
				transformer.transform(source, result);
			}
		}
		catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		}
		catch (TransformerException tfe)
		{
			tfe.printStackTrace();
		}
	}


	private void appendProcesses(Document doc, Element rootElement, String workflowName) {
		try
		{
			// Get the list of processes
			Set<ProcessReader> set = monitor.getProcesses();
			
			// For each process print related data
			for (ProcessReader wfr: set)
			{
				if(wfr.getWorkflow().getName() == workflowName)
				{
					Element process = doc.createElement("process");
					rootElement.appendChild(process);
					
					process.setAttribute("startAt", String.valueOf(wfr.getStartTime().getTime()));
					/*System.out.println("Process started at " + 
										dateFormat.format(wfr.getStartTime().getTime()) +
							            " "+"- Workflow " + wfr.getWorkflow().getName());
					System.out.println("Status:");*/
					List<ActionStatusReader> statusSet = wfr.getStatus();
	
					for (ActionStatusReader asr : statusSet)
					{
						Element actionStatus = doc.createElement("actionStatus");
						process.appendChild(actionStatus);
						
						Element actionName = doc.createElement("actionName");
						actionName.appendChild(doc.createTextNode(asr.getActionName()));
						actionStatus.appendChild(actionName);
						
						//System.out.print(asr.getActionName()+"\t");
						if (asr.isTakenInCharge())
						{
							Element actor = doc.createElement("actor");
							actionStatus.appendChild(actor);
							actor.setAttribute("name", asr.getActor().getName());
							//System.out.print(asr.getActor().getName()+"\t\t");
							Element role = doc.createElement("role");
							role.appendChild(doc.createTextNode(asr.getActor().getRole()));
							actor.appendChild(role);
						}
		
						if (asr.isTerminated())
							actionStatus.setAttribute("terminatedAt", String.valueOf(dateFormat.format(asr.getTerminationTime().getTime())));
							//System.out.println(dateFormat.format(asr.getTerminationTime().getTime()));
		
					}
				}
			}
		}
		catch(Exception ex)
		{
			
		}
	}


	private boolean appendData(Document doc, Element rootElement) {
		try
		{
			Set<WorkflowReader> set = monitor.getWorkflows();
			
			for (WorkflowReader wfr: set)
			{
				Element workflow = doc.createElement("workflow");
				rootElement.appendChild(workflow);
				
				workflow.setAttribute("name", wfr.getName());
				
				appendProcesses(doc, workflow, wfr.getName());
				
				// Print actions
				//System.out.println("Actions:");
				Set<ActionReader> setAct = wfr.getActions();
				//printHeader("Action Name\tRole\t\tAutom.Inst.\tSimple/Process\tWorkflow\tNext Possible Actions");
				for (ActionReader ar: setAct)
				{
					Element action = doc.createElement("action");
					workflow.appendChild(action);
					
					Element role = doc.createElement("role");
					role.appendChild(doc.createTextNode(ar.getRole()));
					action.appendChild(role);
					
					action.setAttribute("name", ar.getName());
					action.setAttribute("automInst", String.valueOf(ar.isAutomaticallyInstantiated()));
					
					if (ar instanceof SimpleActionReader)
					{
						action.setAttribute("type", "Simple");
						
						Set<ActionReader> setNxt = ((SimpleActionReader)ar).getPossibleNextActions();
						if(!setNxt.isEmpty())
						{
							Element followingActions = doc.createElement("followingActions");
							action.appendChild(followingActions);
							
							for (ActionReader nAct: setNxt)
							{
								Element actionName = doc.createElement("actionName");
								actionName.appendChild(doc.createTextNode(nAct.getName()));
								followingActions.appendChild(actionName);
							}
						}
					}
					else if (ar instanceof ProcessActionReader)
					{
						action.setAttribute("type", "Process");
						
						Element nestedWorkflow = doc.createElement("nestedWorkflow");
						nestedWorkflow.setAttribute("workflowName", ((ProcessActionReader)ar).getActionWorkflow().getName());
						action.appendChild(nestedWorkflow);
					}
				}
			}
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
}

