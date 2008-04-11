  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit TestResult</title>
    </head>
    <body>
        <div class="body">
            <h1>Edit TestResult</h1>
            <div class="nav">
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                %{--<span class="menuButton"><g:link class="list" action="list">TestResult List</g:link></span>--}%
                <span class="menuButton"><g:link class="create" action="create">New TestResult</g:link></span>
            </div>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${testResult}">
            <div class="errors">
                <g:renderErrors bean="${testResult}" as="list" />
            </div>
            </g:hasErrors>
            <g:form controller="testResult" method="post"  enctype="multipart/form-data">
                <input type="hidden" name="id" value="${testResult?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='name'>Name:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'name','errors')}'>
                                    <input type="text" id='name' name='name' value="${fieldValue(bean:testResult,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='description'>Description:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'description','errors')}'>
                                    <textarea rows='5' cols='40' name='description'>${testResult?.description?.encodeAsHTML()}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='author'>Author:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'author','errors')}'>
                                    <input type="text" id='author' name='author' value="${fieldValue(bean:testResult,field:'author')}"/>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='ownerName'>Owner Name:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'ownerName','errors')}'>
                                    <input type="text" id='ownerName' name='ownerName' value="${fieldValue(bean:testResult,field:'ownerName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='ownerEmail'>Owner Email:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'ownerEmail','errors')}'>
                                    <input type="text" id='ownerEmail' name='ownerEmail' value="${fieldValue(bean:testResult,field:'ownerEmail')}"/>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='parent'>Parent:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'parent','errors')}'>
                                    <g:select optionKey="id" from="${ConfigurationItem.list()}" name='parent.id' value="${testResult?.parent?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='configurationItems'>Configuration Items:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'configurationItems','errors')}'>
                                    
<ul>
<g:each var='c' in='${testResult?.configurationItems?}'>
    <li><g:link controller='configurationItem' action='show' id='${c.id}'>${c}</g:link></li>
</g:each>
</ul>
<g:link controller='configurationItem' params='["testResult.id":testResult?.id]' action='create'>Add ConfigurationItem</g:link>

                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='environments'>Environments:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'environments','errors')}'>
                                    
<ul>
<g:each var='e' in='${testResult?.environments?}'>
    <li><g:link controller='environment' action='show' id='${e.id}'>${e}</g:link></li>
</g:each>
</ul>
<g:link controller='environment' params='["testResult.id":testResult?.id]' action='create'>Add Environment</g:link>

                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='statuses'>Statuses:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'statuses','errors')}'>
                                    
<ul>
<g:each var='s' in='${testResult?.statuses?}'>
    <li><g:link controller='status' action='show' id='${s.id}'>${s}</g:link></li>
</g:each>
</ul>
<g:link controller='status' params='["testResult.id":testResult?.id]' action='create'>Add Status</g:link>

                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='thisRelations'>This Relations:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'thisRelations','errors')}'>
                                    
<ul>
<g:each var='t' in='${testResult?.thisRelations?}'>
    <li><g:link controller='relation' action='show' id='${t.id}'>${t}</g:link></li>
</g:each>
</ul>
<g:link controller='relation' params='["testResult.id":testResult?.id]' action='create'>Add Relation</g:link>

                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='thatRelations'>That Relations:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'thatRelations','errors')}'>
                                    
<ul>
<g:each var='t' in='${testResult?.thatRelations?}'>
    <li><g:link controller='relation' action='show' id='${t.id}'>${t}</g:link></li>
</g:each>
</ul>
<g:link controller='relation' params='["testResult.id":testResult?.id]' action='create'>Add Relation</g:link>

                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='document'>Document:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'document','errors')}'>
                                    <input type='file' id='document' name='document' />
                                </td>
                            </tr> 
                        
                            %{--<tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='fileType'>File Type:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'fileType','errors')}'>
                                    <input type="text" id='fileType' name='fileType' value="${fieldValue(bean:testResult,field:'fileType')}"/>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='fileName'>File Name:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'fileName','errors')}'>
                                    <input type="text" id='fileName' name='fileName' value="${fieldValue(bean:testResult,field:'fileName')}"/>
                                </td>
                            </tr>--}%
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='testResultType'>Test Result Type:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:testResult,field:'testResultType','errors')}'>
                                    <g:select optionKey="id" from="${TestResultType.list()}" name='testResultType.id' value="${testResult?.testResultType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
