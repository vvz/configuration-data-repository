<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Edit Change Request</title>
</head>
<body>
<div class="body">
    <h1>Edit Change Request</h1>
    <div class="nav">
        %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
        %{--<span class="menuButton"><g:link class="list" action="list">Change Request List</g:link></span>--}%
        <span class="menuButton"><g:link class="create" action="create">New Change Request</g:link></span>
    </div>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${changeRequest}">
        <div class="errors">
            <g:renderErrors bean="${changeRequest}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="changeRequest" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${changeRequest?.id}"/>
        <div class="dialog">
            <table>
                <tbody>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='name'>Name:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'name', 'errors')}'>
                            <input type="text" id='name' name='name' value="${fieldValue(bean: changeRequest, field: 'name')}"/>
                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='description'>Description:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'description', 'errors')}'>
                            <textarea rows='5' cols='40' name='description'>${changeRequest?.description?.encodeAsHTML()}</textarea>
                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='author'>Author:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'author', 'errors')}'>
                            <input type="text" id='author' name='author' value="${fieldValue(bean: changeRequest, field: 'author')}"/>
                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='ownerName'>Owner Name:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'ownerName', 'errors')}'>
                            <input type="text" id='ownerName' name='ownerName' value="${fieldValue(bean: changeRequest, field: 'ownerName')}"/>
                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='ownerEmail'>Owner Email:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'ownerEmail', 'errors')}'>
                            <input type="text" id='ownerEmail' name='ownerEmail' value="${fieldValue(bean: changeRequest, field: 'ownerEmail')}"/>
                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='parent'>Parent:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'parent', 'errors')}'>
                            <g:select optionKey="id" from="${ConfigurationItem.list()}" name='parent.id' value="${changeRequest?.parent?.id}" noSelection="['null':'']"></g:select>
                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='configurationItems'>Configuration Items:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'configurationItems', 'errors')}'>

                            <ul>
                                <g:each var='c' in='${changeRequest?.configurationItems?}'>
                                    <li><g:link controller='configurationItem' action='show' id='${c.id}'>${c}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller='configurationItem' params='["changeRequest.id":changeRequest?.id]' action='create'>Add ConfigurationItem</g:link>

                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='environments'>Environments:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'environments', 'errors')}'>

                            <ul>
                                <g:each var='e' in='${changeRequest?.environments?}'>
                                    <li><g:link controller='environment' action='show' id='${e.id}'>${e}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller='environment' params='["changeRequest.id":changeRequest?.id]' action='create'>Add Environment</g:link>

                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='statuses'>Statuses:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'statuses', 'errors')}'>

                            <ul>
                                <g:each var='s' in='${changeRequest?.statuses?}'>
                                    <li><g:link controller='status' action='show' id='${s.id}'>${s}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller='status' params='["changeRequest.id":changeRequest?.id]' action='create'>Add Status</g:link>

                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='thisRelations'>This Relations:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'thisRelations', 'errors')}'>

                            <ul>
                                <g:each var='t' in='${changeRequest?.thisRelations?}'>
                                    <li><g:link controller='relation' action='show' id='${t.id}'>${t}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller='relation' params='["changeRequest.id":changeRequest?.id]' action='create'>Add Relation</g:link>

                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='thatRelations'>That Relations:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'thatRelations', 'errors')}'>

                            <ul>
                                <g:each var='t' in='${changeRequest?.thatRelations?}'>
                                    <li><g:link controller='relation' action='show' id='${t.id}'>${t}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller='relation' params='["changeRequest.id":changeRequest?.id]' action='create'>Add Relation</g:link>

                        </td>
                    </tr>

                    <tr class='prop'>
                        <td valign='top' class='name'>
                            <label for='document'>Document:</label>
                        </td>
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'document', 'errors')}'>
                            <input type='file' id='document' name='document'/>
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
                        <td valign='top' class='value ${hasErrors(bean: changeRequest, field: 'requestType', 'errors')}'>
                            <g:select optionKey="id" from="${RequestType.findAllByType('Change Request')}" name='requestType.id' value="${changeRequest?.requestType?.id}"></g:select>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" value="Update"/></span>
            <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
