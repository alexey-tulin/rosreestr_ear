
package ru.rosreestr.client.isur.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.mos.asguf.rkis_gu.coordinate.v5 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ServiceHeader_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "ServiceHeader");
    private final static QName _TaskMessage_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "TaskMessage");
    private final static QName _Message_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "Message");
    private final static QName _RequestsMessage_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "RequestsMessage");
    private final static QName _TasksMessage_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "TasksMessage");
    private final static QName _StatusMessage_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "StatusMessage");
    private final static QName _StatusesMessage_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "StatusesMessage");
    private final static QName _FileIsFileInStore_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "IsFileInStore");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.mos.asguf.rkis_gu.coordinate.v5
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceDocument }
     * 
     */
    public ServiceDocument createServiceDocument() {
        return new ServiceDocument();
    }

    /**
     * Create an instance of {@link RequestInfo }
     * 
     */
    public RequestInfo createRequestInfo() {
        return new RequestInfo();
    }

    /**
     * Create an instance of {@link ServiceDocumentNoFiles }
     * 
     */
    public ServiceDocumentNoFiles createServiceDocumentNoFiles() {
        return new ServiceDocumentNoFiles();
    }

    /**
     * Create an instance of {@link DocumentsRequestData }
     * 
     */
    public DocumentsRequestData createDocumentsRequestData() {
        return new DocumentsRequestData();
    }

    /**
     * Create an instance of {@link CoordinateData }
     * 
     */
    public CoordinateData createCoordinateData() {
        return new CoordinateData();
    }

    /**
     * Create an instance of {@link CoordinateTaskData }
     * 
     */
    public CoordinateTaskData createCoordinateTaskData() {
        return new CoordinateTaskData();
    }

    /**
     * Create an instance of {@link Headers }
     * 
     */
    public Headers createHeaders() {
        return new Headers();
    }

    /**
     * Create an instance of {@link ArrayOfCoordinateData }
     * 
     */
    public ArrayOfCoordinateData createArrayOfCoordinateData() {
        return new ArrayOfCoordinateData();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link ErrorMessageData }
     * 
     */
    public ErrorMessageData createErrorMessageData() {
        return new ErrorMessageData();
    }

    /**
     * Create an instance of {@link ArrayOfCoordinateTaskData }
     * 
     */
    public ArrayOfCoordinateTaskData createArrayOfCoordinateTaskData() {
        return new ArrayOfCoordinateTaskData();
    }

    /**
     * Create an instance of {@link GetRequestsResponse }
     * 
     */
    public GetRequestsResponse createGetRequestsResponse() {
        return new GetRequestsResponse();
    }

    /**
     * Create an instance of {@link GetRequestsOutMessage }
     * 
     */
    public GetRequestsOutMessage createGetRequestsOutMessage() {
        return new GetRequestsOutMessage();
    }

    /**
     * Create an instance of {@link ArrayOfSetFilesAndStatusesData }
     * 
     */
    public ArrayOfSetFilesAndStatusesData createArrayOfSetFilesAndStatusesData() {
        return new ArrayOfSetFilesAndStatusesData();
    }

    /**
     * Create an instance of {@link CoordinateStatusData }
     * 
     */
    public CoordinateStatusData createCoordinateStatusData() {
        return new CoordinateStatusData();
    }

    /**
     * Create an instance of {@link GetRequestList }
     * 
     */
    public GetRequestList createGetRequestList() {
        return new GetRequestList();
    }

    /**
     * Create an instance of {@link GetRequestListInMessage }
     * 
     */
    public GetRequestListInMessage createGetRequestListInMessage() {
        return new GetRequestListInMessage();
    }

    /**
     * Create an instance of {@link GetRequests }
     * 
     */
    public GetRequests createGetRequests() {
        return new GetRequests();
    }

    /**
     * Create an instance of {@link GetRequestsInMessage }
     * 
     */
    public GetRequestsInMessage createGetRequestsInMessage() {
        return new GetRequestsInMessage();
    }

    /**
     * Create an instance of {@link GetRequestListResponse }
     * 
     */
    public GetRequestListResponse createGetRequestListResponse() {
        return new GetRequestListResponse();
    }

    /**
     * Create an instance of {@link GetRequestListOutMessage }
     * 
     */
    public GetRequestListOutMessage createGetRequestListOutMessage() {
        return new GetRequestListOutMessage();
    }

    /**
     * Create an instance of {@link RequestStatus }
     * 
     */
    public RequestStatus createRequestStatus() {
        return new RequestStatus();
    }

    /**
     * Create an instance of {@link ArrayOfRequestContact }
     * 
     */
    public ArrayOfRequestContact createArrayOfRequestContact() {
        return new ArrayOfRequestContact();
    }

    /**
     * Create an instance of {@link ArrayOfFile }
     * 
     */
    public ArrayOfFile createArrayOfFile() {
        return new ArrayOfFile();
    }

    /**
     * Create an instance of {@link RequestContact }
     * 
     */
    public RequestContact createRequestContact() {
        return new RequestContact();
    }

    /**
     * Create an instance of {@link RequestResult }
     * 
     */
    public RequestResult createRequestResult() {
        return new RequestResult();
    }

    /**
     * Create an instance of {@link ServiceNumberStatusesOnly }
     * 
     */
    public ServiceNumberStatusesOnly createServiceNumberStatusesOnly() {
        return new ServiceNumberStatusesOnly();
    }

    /**
     * Create an instance of {@link SmallRequestInfo }
     * 
     */
    public SmallRequestInfo createSmallRequestInfo() {
        return new SmallRequestInfo();
    }

    /**
     * Create an instance of {@link ArrayOfRequestStatus }
     * 
     */
    public ArrayOfRequestStatus createArrayOfRequestStatus() {
        return new ArrayOfRequestStatus();
    }

    /**
     * Create an instance of {@link ArrayOfServiceNumberStatusesOnly }
     * 
     */
    public ArrayOfServiceNumberStatusesOnly createArrayOfServiceNumberStatusesOnly() {
        return new ArrayOfServiceNumberStatusesOnly();
    }

    /**
     * Create an instance of {@link SetFilesAndStatusesData }
     * 
     */
    public SetFilesAndStatusesData createSetFilesAndStatusesData() {
        return new SetFilesAndStatusesData();
    }

    /**
     * Create an instance of {@link Department }
     * 
     */
    public Department createDepartment() {
        return new Department();
    }

    /**
     * Create an instance of {@link RequestQueryTask }
     * 
     */
    public RequestQueryTask createRequestQueryTask() {
        return new RequestQueryTask();
    }

    /**
     * Create an instance of {@link ArrayOfNote }
     * 
     */
    public ArrayOfNote createArrayOfNote() {
        return new ArrayOfNote();
    }

    /**
     * Create an instance of {@link File }
     * 
     */
    public File createFile() {
        return new File();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link Note }
     * 
     */
    public Note createNote() {
        return new Note();
    }

    /**
     * Create an instance of {@link ArrayOfSmallRequestInfo }
     * 
     */
    public ArrayOfSmallRequestInfo createArrayOfSmallRequestInfo() {
        return new ArrayOfSmallRequestInfo();
    }

    /**
     * Create an instance of {@link ArrayOfServiceDocumentNoFiles }
     * 
     */
    public ArrayOfServiceDocumentNoFiles createArrayOfServiceDocumentNoFiles() {
        return new ArrayOfServiceDocumentNoFiles();
    }

    /**
     * Create an instance of {@link RequestAccount }
     * 
     */
    public RequestAccount createRequestAccount() {
        return new RequestAccount();
    }

    /**
     * Create an instance of {@link ArrayOfRequestInfo }
     * 
     */
    public ArrayOfRequestInfo createArrayOfRequestInfo() {
        return new ArrayOfRequestInfo();
    }

    /**
     * Create an instance of {@link RequestServiceNoFiles }
     * 
     */
    public RequestServiceNoFiles createRequestServiceNoFiles() {
        return new RequestServiceNoFiles();
    }

    /**
     * Create an instance of {@link RequestTask }
     * 
     */
    public RequestTask createRequestTask() {
        return new RequestTask();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link RequestService }
     * 
     */
    public RequestService createRequestService() {
        return new RequestService();
    }

    /**
     * Create an instance of {@link ArrayOfServiceDocument }
     * 
     */
    public ArrayOfServiceDocument createArrayOfServiceDocument() {
        return new ArrayOfServiceDocument();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link ServiceDocument.CustomAttributes }
     * 
     */
    public ServiceDocument.CustomAttributes createServiceDocumentCustomAttributes() {
        return new ServiceDocument.CustomAttributes();
    }

    /**
     * Create an instance of {@link RequestInfo.CustomAttributes }
     * 
     */
    public RequestInfo.CustomAttributes createRequestInfoCustomAttributes() {
        return new RequestInfo.CustomAttributes();
    }

    /**
     * Create an instance of {@link ServiceDocumentNoFiles.CustomAttributes }
     * 
     */
    public ServiceDocumentNoFiles.CustomAttributes createServiceDocumentNoFilesCustomAttributes() {
        return new ServiceDocumentNoFiles.CustomAttributes();
    }

    /**
     * Create an instance of {@link DocumentsRequestData.Parameter }
     * 
     */
    public DocumentsRequestData.Parameter createDocumentsRequestDataParameter() {
        return new DocumentsRequestData.Parameter();
    }

    /**
     * Create an instance of {@link CoordinateData.CustomAttributes }
     * 
     */
    public CoordinateData.CustomAttributes createCoordinateDataCustomAttributes() {
        return new CoordinateData.CustomAttributes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Headers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "ServiceHeader")
    public JAXBElement<Headers> createServiceHeader(Headers value) {
        return new JAXBElement<Headers>(_ServiceHeader_QNAME, Headers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateTaskData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "TaskMessage")
    public JAXBElement<CoordinateTaskData> createTaskMessage(CoordinateTaskData value) {
        return new JAXBElement<CoordinateTaskData>(_TaskMessage_QNAME, CoordinateTaskData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "Message")
    public JAXBElement<CoordinateData> createMessage(CoordinateData value) {
        return new JAXBElement<CoordinateData>(_Message_QNAME, CoordinateData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCoordinateData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "RequestsMessage")
    public JAXBElement<ArrayOfCoordinateData> createRequestsMessage(ArrayOfCoordinateData value) {
        return new JAXBElement<ArrayOfCoordinateData>(_RequestsMessage_QNAME, ArrayOfCoordinateData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCoordinateTaskData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "TasksMessage")
    public JAXBElement<ArrayOfCoordinateTaskData> createTasksMessage(ArrayOfCoordinateTaskData value) {
        return new JAXBElement<ArrayOfCoordinateTaskData>(_TasksMessage_QNAME, ArrayOfCoordinateTaskData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateStatusData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "StatusMessage")
    public JAXBElement<CoordinateStatusData> createStatusMessage(CoordinateStatusData value) {
        return new JAXBElement<CoordinateStatusData>(_StatusMessage_QNAME, CoordinateStatusData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSetFilesAndStatusesData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "StatusesMessage")
    public JAXBElement<ArrayOfSetFilesAndStatusesData> createStatusesMessage(ArrayOfSetFilesAndStatusesData value) {
        return new JAXBElement<ArrayOfSetFilesAndStatusesData>(_StatusesMessage_QNAME, ArrayOfSetFilesAndStatusesData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "IsFileInStore", scope = File.class)
    public JAXBElement<Boolean> createFileIsFileInStore(Boolean value) {
        return new JAXBElement<Boolean>(_FileIsFileInStore_QNAME, Boolean.class, File.class, value);
    }

}
