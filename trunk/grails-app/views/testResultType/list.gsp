  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>TestResultType List</title>
    </head>
    <body>
        <div class="body">
            <h1>TestResultType List</h1>
            <div class="nav">%{----}%
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                <span class="menuButton"><g:link class="create" action="create">New TestResultType</g:link></span>
            </div>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="order" title="Order" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${testResultTypeList}" status="i" var="testResultType">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            %{--<td><g:link action="show" id="${testResultType.id}">${testResultType.id?.encodeAsHTML()}</g:link></td>--}%
                        
                            <td><g:link action="show" id="${testResultType.id}">${testResultType.description?.encodeAsHTML()}</g:link></td>
                        
                            <td>${testResultType.order?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${TestResultType.count()}" />
            </div>
        </div>
    </body>
</html>
