CREATE  table ${tableName} (
${sql}
}
EXECUTE sp_addextendedproperty 'MS_Description', '${template}', 'user', 'dbo', 'table', '${tableName}', NULL, NULL;
${sqlserver_comment}