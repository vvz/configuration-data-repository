
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Create ChangeRequest</title>
    </head>
    <body>
        <div class="body">
            <h1>Create ChangeRequest</h1>
            <div class="nav">
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                <span class="menuButton"><g:link class="list" action="list">ChangeRequest List</g:link></span>
            </div>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${changeRequest}">
                <div class="errors">
                    <g:renderErrors bean="${changeRequest}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form action="save" method="post"  enctype="multipart/form-data">
                <div class="dialog">
                    <table>
                        <tbody>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='name'>Name:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'name','errors')}'>
                            <input type="text" id='name' name='name' value="${fieldValue(bean:changeRequest,field:'name')}"/>
                            </td>
                            </tr>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='description'>Description:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'description','errors')}'>
                            <textarea rows='5' cols='40' name='description'>${changeRequest?.description?.encodeAsHTML()}</textarea>
                            </td>
                            </tr>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='author'>Author:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'author','errors')}'>
                            <input type="text" id='author' name='author' value="${fieldValue(bean:changeRequest,field:'author')}"/>
                            </td>
                            </tr>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='ownerName'>Owner Name:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'ownerName','errors')}'>
                            <input type="text" id='ownerName' name='ownerName' value="${fieldValue(bean:changeRequest,field:'ownerName')}"/>
                            </td>
                            </tr>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='ownerEmail'>Owner Email:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'ownerEmail','errors')}'>
                            <input type="text" id='ownerEmail' name='ownerEmail' value="${fieldValue(bean:changeRequest,field:'ownerEmail')}"/>
                            </td>
                            </tr>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='parent'>Parent:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'parent','errors')}'>
                            <g:select optionKey="id" from="${ConfigurationItem.list()}" name='parent.id' value="${changeRequest?.parent?.id}" noSelection="['null':'']"></g:select>
                            </td>
                            </tr>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='document'>Document:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'document','errors')}'>
                            <input type='file' id='document' name='document' />
                            </td>
                            </tr>
                            
                            %{--<tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='fileType'>File Type:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'fileType','errors')}'>
                            <input type="text" id='fileType' name='fileType' value="${fieldValue(bean:changeRequest,field:'fileType')}"/>
                            </td>
                            </tr>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='fileName'>File Name:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'fileName','errors')}'>
                            <input type="text" id='fileName' name='fileName' value="${fieldValue(bean:changeRequest,field:'fileName')}"/>
                            </td>
                            </tr>--}%
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='requestType'>Request Type:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:changeRequest,field:'requestType','errors')}'>
                            <g:select optionKey="id" from="${RequestType.findAllByType('Change Request')}" name='requestType.id' value="${changeRequest?.requestType?.id}" ></g:select>
                            </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create"></input></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
