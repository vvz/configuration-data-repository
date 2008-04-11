  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit RelationReference</title>
    </head>
    <body>
        <div class="body">
            <h1>Edit RelationReference</h1>
            <div class="nav">
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                %{--<span class="menuButton"><g:link class="list" action="list">RelationReference List</g:link></span>--}%
                <span class="menuButton"><g:link class="create" action="create">New RelationReference</g:link></span>
            </div>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${relationReference}">
            <div class="errors">
                <g:renderErrors bean="${relationReference}" as="list" />
            </div>
            </g:hasErrors>
            <g:form controller="relationReference" method="post" >
                <input type="hidden" name="id" value="${relationReference?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='name'>Name:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:relationReference,field:'name','errors')}'>
                                    <input type="text" id='name' name='name' value="${fieldValue(bean:relationReference,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='description'>Description:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:relationReference,field:'description','errors')}'>
                                    <input type="text" id='description' name='description' value="${fieldValue(bean:relationReference,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='order'>Order:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:relationReference,field:'order','errors')}'>
                                    <input type='text' id='order' name='order' value="${fieldValue(bean:relationReference,field:'order')}" />
                                </td>
                            </tr> 
                        
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='relations'>Relations:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:relationReference,field:'relations','errors')}'>
                                    
<ul>
<g:each var='r' in='${relationReference?.relations?}'>
    <li><g:link controller='relation' action='show' id='${r.id}'>${r}</g:link></li>
</g:each>
</ul>
<g:link controller='relation' params='["relationReference.id":relationReference?.id]' action='create'>Add Relation</g:link>

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
