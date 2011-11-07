/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// Copyright (C) 2007-2011 Julian Hyde
// All Rights Reserved.
// You must accept the terms of that agreement to use this software.
*/
package org.olap4j.driver.xmla;

import org.olap4j.OlapException;
import org.olap4j.OlapStatement;
import org.olap4j.driver.xmla.proxy.XmlaOlap4jProxy;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Instantiates classes to implement the olap4j API against the
 * an XML for Analysis provider.
 *
 * <p>There are implementations for JDBC 3.0 (which occurs in JDK 1.5)
 * and JDBC 4.0 (which occurs in JDK 1.6).
 *
 * @author jhyde
 * @version $Id$
 * @since Jun 14, 2007
 */
interface Factory {
    /**
     * Creates a connection.
     *
     * @param driver Driver
     * @param proxy Proxy (for submitting requests, via HTTP or otherwise)
     * @param url URL of server
     * @param info Properties defining the connection
     * @return Connection
     * @throws SQLException on error
     */
    Connection newConnection(
        XmlaOlap4jDriver driver,
        XmlaOlap4jProxy proxy,
        String url,
        Properties info) throws SQLException;

    /**
     * Creates an empty result set.
     *
     * @param olap4jConnection Connection
     * @return Result set
     */
    EmptyResultSet newEmptyResultSet(
        XmlaOlap4jConnection olap4jConnection);

    /**
     * Creates a result set with a fixed set of rows.
     *
     * @param olap4jConnection Connection
     * @param headerList Column headers
     * @param rowList Row values
     * @return Result set
     */
    ResultSet newFixedResultSet(
        XmlaOlap4jConnection olap4jConnection,
        List<String> headerList,
        List<List<Object>> rowList);

    /**
     * Creates a cell set.
     *
     * @param olap4jStatement Statement
     * @return Cell set
     * @throws OlapException on error
     */
    XmlaOlap4jCellSet newCellSet(
        XmlaOlap4jStatement olap4jStatement) throws OlapException;

    /**
     * Creates a statement.
     *
     * @param olap4jConnection Connection
     * @return Statement
     */
    XmlaOlap4jStatement newStatement(
        XmlaOlap4jConnection olap4jConnection);

    /**
     * Creates a prepared statement.
     *
     * @param mdx MDX query text
     * @param olap4jConnection Connection
     * @return Prepared statement
     * @throws OlapException on error
     */
    XmlaOlap4jPreparedStatement newPreparedStatement(
        String mdx,
        XmlaOlap4jConnection olap4jConnection) throws OlapException;

    /**
     * Creates a metadata object.
     *
     * @param olap4jConnection Connection
     * @return Metadata object
     */
    XmlaOlap4jDatabaseMetaData newDatabaseMetaData(
        XmlaOlap4jConnection olap4jConnection);
}

// End Factory.java
