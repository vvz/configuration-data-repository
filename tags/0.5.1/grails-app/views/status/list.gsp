  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Status List</title>
    </head>
    <body>
        <div class="nav">
            %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Status</g:link></span>--}%
        </div>
        <div class="body">
            <h1>Status List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                   	        <g:sortableColumn property="startDate" title="Start Date" />
                   	        <g:sortableColumn property="endDate" title="End Date" />
                   	        <th>Configuration Item</th>
                   	        <th>Reference</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${statusList}" status="i" var="status">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${status.id}">${status.startDate?.encodeAsHTML()}</g:link></td>
                            <td>${status.endDate?.encodeAsHTML()}</td>
                            <td>${status.configurationItem?.encodeAsHTML()}</td>
                            <td>${status.reference?.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Status.count()}" />
            </div>
        </div>
    </body>
</html>
