package model.IO;

import utils.Action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class DBIO {
    protected static final String PREPARATION_ACTION_OPT;
    protected static final String RESULT_ACTION_OPT;
    protected static final String QUERY_TYPE_OPT;
    protected static final String CONNECTION;
    protected static final String RESULT;

    static {
        PREPARATION_ACTION_OPT = "PREPARATION_ACTION";
        RESULT_ACTION_OPT = "RESULT_ACTION";
        QUERY_TYPE_OPT = "QUERY_TYPE";
        CONNECTION = "CONNECTION";
        RESULT = "RESULT";
    }

    public enum QueryType {
        SELECT,
        UPDATE
    }

    private DBIO() {}

    protected static synchronized void executeQuery(Map<String, Object> params, String query) {
        if(query == null || query.isEmpty()) {
            throw new RuntimeException("La query es nula o está vacía.");
        }

        if(params.get(CONNECTION) == null) {
            exec(params, query);
        }
        else {
            execWithConnection(params, query);
        }
        params.clear();
    }

    private static synchronized void exec(Map<String, Object> params, String query) {
        try (Connection conn = DBManager.getConnection()) {
            params.put(CONNECTION, conn);
            execWithConnection(params, query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static synchronized void execWithConnection(Map<String, Object> params, String query) {
        Connection conn = (Connection) params.get(CONNECTION);
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ((Action) params.get(PREPARATION_ACTION_OPT)).execute(stmt);
            switch ((QueryType) params.get(QUERY_TYPE_OPT)) {
                case SELECT: {
                    try (ResultSet result = stmt.executeQuery()) {
                        ((Action) params.get(RESULT_ACTION_OPT)).execute(result);
                    }
                }
                break;
                case UPDATE: {
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static class SelectBuilder {
        HashMap<String, Object> params;
        String query;
        boolean done;

        public SelectBuilder() {
            params = new HashMap<>();
            params.put(DBIO.QUERY_TYPE_OPT, DBIO.QueryType.SELECT);
        }

        public void run() {
            if(done) {
                DBIO.executeQuery(params, query);
            } else {
                throw new RuntimeException("DB Operation is not ready to be ran.");
            }
        }

        public SelectBuilder withPrepareAction(Action action) {
            this.params.put(DBIO.PREPARATION_ACTION_OPT, action);
            return this;
        }

        public SelectBuilder withResultAction(Action action) {
            this.params.put(DBIO.RESULT_ACTION_OPT, action);
            return this;
        }

        public SelectBuilder withQuery(String query) {
            this.query = query;
            return this;
        }

        public void setDone() {
            this.done = true;
        }

    }

    public static class UpdateBuilder {
        HashMap<String, Object> params;
        String query;
        boolean done;

        public UpdateBuilder() {
            params = new HashMap<>();
            params.put(DBIO.QUERY_TYPE_OPT, QueryType.UPDATE);
        }

        public void run() {
            if(done) {
                DBIO.executeQuery(params, query);
            } else {
                throw new RuntimeException("DB Operation is not ready to be ran.");
            }
        }

        public UpdateBuilder withPrepareAction(Action action) {
            this.params.put(DBIO.PREPARATION_ACTION_OPT, action);
            return this;
        }

        public UpdateBuilder withQuery(String query) {
            this.query = query;
            return this;
        }

        public void setDone() {
            this.done = true;
        }

    }
}
