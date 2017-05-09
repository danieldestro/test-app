
INSERT INTO country values 
	(NEXT VALUE FOR SEQ_COUNTRY, 'BR'),
	(NEXT VALUE FOR SEQ_COUNTRY, 'GB'),
	(NEXT VALUE FOR SEQ_COUNTRY, 'US');

declare @seqProposal sql_variant;
select @seqProposal = NEXT VALUE FOR SEQ_PROPOSAL;
-- select @seqProposal = current_value from sys.sequences where name='SEQ_PROPOSAL';

INSERT INTO proposal VALUES (CAST(@seqProposal as int), 'STANDARD PROPOSAL');

INSERT INTO service VALUES (NEXT VALUE FOR SEQ_SERVICE, 'Service One', CAST(@seqProposal as int), 5);
INSERT INTO service VALUES (NEXT VALUE FOR SEQ_SERVICE, 'Service Two', CAST(@seqProposal as int), 11);