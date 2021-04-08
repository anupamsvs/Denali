/*Init user roles*/
INSERT INTO user_profile(type) VALUES ('SUPERUSER');
INSERT INTO user_profile(type) VALUES ('ADMIN');

/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO app_user(user_profile_id, sso_id, password, first_name, last_name, email)
VALUES (1000000, 'Admin','$2a$10$G3APgq7oOTWO5/cXEwkEzeE/PGgOxK7nlulQ74byA1D8V3VQZsPmm', 'admin','admin','admin@pwc.com');

/*Init work_stream static data*/
INSERT INTO work_stream(description) VALUES('Functional');
INSERT INTO work_stream(description) VALUES('Testing');
INSERT INTO work_stream(description) VALUES('Technical');
INSERT INTO work_stream(description) VALUES('Conversion');
INSERT INTO work_stream(description) VALUES('Change');
INSERT INTO work_stream(description) VALUES('PMO');
INSERT INTO work_stream(description) VALUES('PMO Hub');

/*Init engagement_status static data*/
INSERT INTO engagement_status(description) VALUES('New');
INSERT INTO engagement_status(description) VALUES('Waiting');
INSERT INTO engagement_status(description) VALUES('Ready to Estimate');
INSERT INTO engagement_status(description) VALUES('Estimate Complete');
INSERT INTO engagement_status(description) VALUES('Closed');

/*Init enterprise_application static data*/
INSERT INTO enterprise_application(description) VALUES('Oracle ERP Cloud');
INSERT INTO enterprise_application(description) VALUES('Oracle HCM Cloud');

/*Init business_process static data*/
INSERT INTO business_process(description) VALUES('Acquire to Retire');
INSERT INTO business_process(description) VALUES('Order to Cash');
INSERT INTO business_process(description) VALUES('Procure to Pay');
INSERT INTO business_process(description) VALUES('Project Accounting');
INSERT INTO business_process(description) VALUES('Record to Report');
INSERT INTO business_process(description) VALUES('Supplemental Process Stream');
INSERT INTO business_process(description) VALUES('Human Capital Management');

/*Init conversion_data_type static data*/
INSERT INTO conversion_data_type(description) VALUES('Master Data');
INSERT INTO conversion_data_type(description) VALUES('Transaction Data');

/*Init client_complexity static data*/
INSERT INTO client_complexity(description) VALUES('Low');
INSERT INTO client_complexity(description) VALUES('Medium');
INSERT INTO client_complexity(description) VALUES('High');
INSERT INTO client_complexity(description) VALUES('Very High');

/*Init rice_type static data*/
INSERT INTO rice_type(description) VALUES('Interface');
INSERT INTO rice_type(description) VALUES('PaaS Apps');
INSERT INTO rice_type(description) VALUES('Report');
INSERT INTO rice_type(description) VALUES('Workflow');

/*Init paas_app_type static data*/
INSERT INTO paas_app_type(description) VALUES('N/A');
INSERT INTO paas_app_type(description) VALUES('Data Stage');
INSERT INTO paas_app_type(description) VALUES('UI');
INSERT INTO paas_app_type(description) VALUES('Bus Logic');
INSERT INTO paas_app_type(description) VALUES('Report');

/*Init prototype static data*/
INSERT INTO prototype(description) VALUES('P1-Assessment');
INSERT INTO prototype(description) VALUES('P2-Design & Config');
INSERT INTO prototype(description) VALUES('P3-Refine & Validate');
INSERT INTO prototype(description) VALUES('P4-Test & Deploy');
INSERT INTO prototype(description) VALUES('Post Go-Live Support ');

/*Init service_owner static data*/
INSERT INTO service_owner(description) VALUES('CIO Advisory');
INSERT INTO service_owner(description) VALUES('Finance Transformation');
INSERT INTO service_owner(description) VALUES('Scope Enhancements');
INSERT INTO service_owner(description) VALUES('Assurance');
INSERT INTO service_owner(description) VALUES('Indirect Tax');
INSERT INTO service_owner(description) VALUES('Direct Tax');
INSERT INTO service_owner(description) VALUES('QMT');
INSERT INTO service_owner(description) VALUES('Technical');
INSERT INTO service_owner(description) VALUES('People & Change');
INSERT INTO service_owner(description) VALUES('PMO');
INSERT INTO service_owner(description) VALUES('PMO Hub');
INSERT INTO service_owner(description) VALUES('Other');

/*Init Onsite static data*/
INSERT INTO onsite_csp_rate_metadata(work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost ,associate_cost ,create_date,update_date) 
VALUES(1000000, 570, 390, 270, 190, 150, CURDATE(), CURDATE());

INSERT INTO onsite_csp_rate_metadata(work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost ,associate_cost ,create_date,update_date) 
VALUES(1000001, 570, 390, 270, 190, 150, CURDATE(), CURDATE());

INSERT INTO onsite_csp_rate_metadata(work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost ,associate_cost ,create_date,update_date) 
VALUES(1000002, 570, 390, 270, 190, 150, CURDATE(), CURDATE());

INSERT INTO onsite_csp_rate_metadata(work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost ,associate_cost ,create_date,update_date) 
VALUES(1000003, 570, 390, 270, 190, 150, CURDATE(), CURDATE());

INSERT INTO onsite_csp_rate_metadata(work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost ,associate_cost ,create_date,update_date) 
VALUES(1000004, 570, 390, 270, 190, 150, CURDATE(), CURDATE());

INSERT INTO onsite_csp_rate_metadata(work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost ,associate_cost ,create_date,update_date) 
VALUES(1000005, 570, 390, 270, 190, 150, CURDATE(), CURDATE());

INSERT INTO onsite_csp_rate_metadata(work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost ,associate_cost ,create_date,update_date) 
VALUES(1000006,570, 390, 270, 190, 150, CURDATE(), CURDATE());


/*Init offshore static data*/
INSERT INTO offshore_csp_rate_metadata(work_stream_id,offshore_landed_cost,manager_cost,staff_cost,create_date,update_date) 
VALUES(1000000,75, 50,50, CURDATE(), CURDATE());

INSERT INTO offshore_csp_rate_metadata(work_stream_id,offshore_landed_cost,manager_cost,staff_cost,create_date,update_date) 
VALUES(1000001,75, 50,50, CURDATE(), CURDATE());

INSERT INTO offshore_csp_rate_metadata(work_stream_id,offshore_landed_cost,manager_cost,staff_cost,create_date,update_date) 
VALUES(1000002,75, 50,50, CURDATE(), CURDATE());

INSERT INTO offshore_csp_rate_metadata(work_stream_id,offshore_landed_cost,manager_cost,staff_cost,create_date,update_date) 
VALUES(1000003,75, 50,50, CURDATE(), CURDATE());

INSERT INTO offshore_csp_rate_metadata(work_stream_id,offshore_landed_cost,manager_cost,staff_cost,create_date,update_date) 
VALUES(1000004,75, 50,50, CURDATE(), CURDATE());

INSERT INTO offshore_csp_rate_metadata(work_stream_id,offshore_landed_cost,manager_cost,staff_cost,create_date,update_date) 
VALUES(1000005,75, 50,50, CURDATE(), CURDATE());

INSERT INTO offshore_csp_rate_metadata(work_stream_id,offshore_landed_cost,manager_cost,staff_cost,create_date,update_date) 
VALUES(1000006,75, 50,50, CURDATE(), CURDATE());

/*module_metadata Acquire to Retire*/
INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000000, 'Fusion Financials - Assets','FA', 1, 0.6, 'Yes', CURDATE(),CURDATE(),'No',1.0,407,8);

/*module_metadata Order to Cash*/
INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000001, 'Fusion Financials - Receivables','AR', 1.25, 1, 'Yes', CURDATE(),CURDATE(),'No',1.0,470,8);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000001, 'Fusion Inventory Management','INV', 1.25, 1, 'No', CURDATE(),CURDATE(),'No',1.0,228,5);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000001, 'Fusion Advanced Collections','IEX', 1, 0.4, 'No', CURDATE(),CURDATE(),'No',1.0,38,2);

/**module_metadata Procure to Pay*/
INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002,  'Fusion Financials - Payables','AP', 1.25, 0.8, 'Yes',  CURDATE(),CURDATE(),'No',1.0,413,8);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002,  'Fusion Financials - Payments','IBY', 1, 0.2, 'Yes', CURDATE(),CURDATE(),'No',1.0,30,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Purchasing','PO', 1.25, 1, 'Yes', CURDATE(),CURDATE(),'No',1.0,198,5);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Self Services Procurement','SS Proc', 1, 0.6, 'Yes',CURDATE(),CURDATE(),'No',1.0,72,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Expenses','Exp', 1, 0.5, 'Yes',CURDATE(),CURDATE(),'No',1.0,57,3);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Financials - Tax','Tax', 1, 0.5, 'Yes',  CURDATE(),CURDATE(),'No',1.0,247,8);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Sourcing','SRC', 1, 0.4, 'No', CURDATE(),CURDATE(),'No',1.0,106,4);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps)
VALUES(1000002, 'Fusion Procurement Contracts','PRC', 1, 0.4, 'No', CURDATE(),CURDATE(),'No',1.0,97,3);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Supplier Portal','SPP', 1, 0.4, 'No',CURDATE(),CURDATE(),'No',1.0,41,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Webcenter Forms','WCF', 1, 0.4, 'No', CURDATE(),CURDATE(),'No',1.0,81,4);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Supplier Qualification','SQF', 1, 0.4, 'No', CURDATE(),CURDATE(),'No',1.0,40,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000002, 'Fusion Supplier Management','SMG', 1, 0.4, 'No', CURDATE(),CURDATE(),'No',1.0,60,4);

/*module_metadata Project Accounting*/
INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000003,  'Fusion Project Costing','PJC', 1.25, 0.6, 'Yes', CURDATE(),CURDATE(),'No',1.0,60,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps)
VALUES(1000003,  'Fusion Enterprise Contract','PJCN', 1, 0.2, 'Yes',  CURDATE(),CURDATE(),'No',1.0,17,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps)
VALUES(1000003, 'Fusion Project Billing','PJB', 1.25, 0.6, 'Yes', CURDATE(),CURDATE(),'No',1.0,10,1);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000003, 'Fusion Project Foundation','PJF', 1, 0.6, 'Yes', CURDATE(),CURDATE(),'No',1.0,30,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000003,  'Fusion Project Performance Reporting','PJPR', 1, 0.2, 'Yes', CURDATE(),CURDATE(),'No',1.0,435,8);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps)
VALUES(1000003, 'Fusion Time & Labor for Projects','PJTL', 1, 0.4, 'Yes', CURDATE(),CURDATE(),'No',1.0,99,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000003, 'Fusion Project Control','PJCTL', 1, 0.4, 'Yes', CURDATE(),CURDATE(),'No',1.0,10,2);

/*module_metadata Record to Report*/
INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000004, 'Fusion Financials - General Ledger','GL', 1.25, 1,'Yes', CURDATE(),CURDATE(),'No',1.0,10,1);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000004, 'Fusion Financials - Cash Management','CM', 1, 0.4,'Yes',CURDATE(),CURDATE(),'No',1.0,20,2);

INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000004, 'Fusion Financial Reports Center','FRC', 1, 0.4,'Yes', CURDATE(),CURDATE(),'No',1.0,20,2);

/*Supplemental Process Stream*/
INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000005, 'Supplemental Module 4','SM4', 1, 1,'No', CURDATE(),CURDATE(),'No',1.0,0,0);

/*module_metadata Human Capital Management*/
INSERT INTO module_metadata(business_process_id,module_name,module_abbreviation, module_duration_factor, module_weightage, ddc_supported_flag,create_date,update_date,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps) 
VALUES(1000006, 'Human Capital Management','HR', 1, 1,'Yes', CURDATE(),CURDATE(),'No',1.0,1,1);


/*Master conversion*/
INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000000, 'Assets', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000024, 'Banks', CURDATE(),CURDATE(),'No',1000000);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000023, 'COA Values & Hierarchies', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000023, 'Legal Entities', CURDATE(),CURDATE(),'No',1000000);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000004, 'Suppliers', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000004, 'Supplier Banks', CURDATE(),CURDATE(),'No',1000000);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000001, 'Customers', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000002, 'Inventory Items', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000016, 'Projects Header Details', CURDATE(),CURDATE(),'No',1000002);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000000, 1000027, 'Employees', CURDATE(),CURDATE(),'No',1000000);
/*
insert into conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date)
values(1000000, );
*/

/*Transaction conversion*/
INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000023, 'Current Year Transactions', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000023, 'Prior Year Summary', CURDATE(),CURDATE(),'No',1000000);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000004, 'Open AP Invoices', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000001, 'Open AR Invoices', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000001, 'Unapplied AR Cash Receipts', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000002, 'Inventory On Hand Quantities', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000022, 'Project Budgets', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000016, 'Project Costs & Tasks', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000006, 'Open Purchase Orders including Un-Invoiced PO/Receipts (Accruals)', CURDATE(),CURDATE(),'No',1000001);

INSERT INTO conversion_metadata(conversion_data_type_id, module_id,conversion_name,create_date,update_date,scope_override_flag,client_complexity_id)
VALUES(1000001, 1000006, 'Open PO Receipts', CURDATE(),CURDATE(),'No',1000000);

/*Funcional PwC Responsibility*/
INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000000, 'Requirements List', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Gaps Deep Dive', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Workshops', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Initial Client Configurations', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Configurations - P2  - Config Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Configurations - P2 - Scenario Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'Configurations - P3 - SIT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000003, 'Configurations - P4 - UAT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'Job Aids', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000000, 'RICE - Func Specs + Func Unit Test - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'RICE - Func Specs + Func Unit Test - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'RICE - Func Specs + Func Unit Test - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000003, 'RICE - Func Specs + Func Unit Test - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000000, 'Conversion - Func Specs + Func Unit Test - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Conversion - Func Specs + Func Unit Test - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'Conversion - Func Specs + Func Unit Test - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000003, 'Conversion - Func Specs + Func Unit Test - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Future Business Process Design', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Test Event Support - P2 - Config Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Test Event Support - P2 - Scenario Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'Test Event Support - P3 - SIT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000003, 'Test Event Support - P4 - UAT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Defect Resolution - P2 - Config Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Defect Resolution - P2 - Scenario Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'Defect Resolution -  - P3 - SIT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000003, 'Defect Resolution - P4 - UAT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Application Access Strategy - P2', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'Application Access Strategy - P3', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000001, 'Test Strategy', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'Application Access User-Role Mapping', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000002, 'Security Configuration Design Document', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000003, 'Deployment Support', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000000, 1000004, 'Conduct Post Go-Live Support', CURDATE(), CURDATE(),'Yes');

/*Testing PwC Responsibility*/
INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000001, 'Test Cases - Functional + Business - P2 - Config Test', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000001, 'Test Cases - Functional + Business - P2 - Scenario Test', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000002, 'Test Cases - Functional + Business - P3', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000003, 'Test Cases - Functional + Business - P4', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000001, 'Test Plan -P2 - Config Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000001, 'Test Plan -P2 - Scenario Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000002, 'Test Plan -P3 - SIT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000003, 'Test Plan -P4 - UAT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000001, 'Test Event -P2 - Config Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000001, 'Test Event -P2 - Scenario Test', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000001, 'Defect Resolution - P2 - Config Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000001, 'Defect Resolution - P2 - Scenario Test', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000002, 'Defect Resolution -  - P3 - SIT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000002, 'Test Event -P3 - SIT', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000003, 'Defect Resolution - P4 - UAT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000003, 'Test Event -P4 - UAT', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000001, 1000004, 'Conduct Post Go-Live Support', CURDATE(), CURDATE(),'Yes');

/*Technical*/
INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000000, 'RIE - Technical Spec + Build - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000001, 'RIE - Technical Spec + Build - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000002, 'RIE - Technical Spec + Build - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000003, 'RIE - Technical Spec + Build - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000000, 'Reference Architecture', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000000, 'Instance Management Strategy', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000000, 'On-Site Technical Coordination - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000001, 'On-Site Technical Coordination - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000002, 'On-Site Technical Coordination - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000003, 'On-Site Technical Coordination - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000004, 'On-Site Technical Coordination - PGL', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000002, 1000004, 'Conduct Post Go-Live Support', CURDATE(), CURDATE(),'Yes');

/*Conversion*/
INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000000, 'Conversion - Technical Spec, Build, Test - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000001, 'Conversion - Technical Spec, Build, Test - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000002, 'Conversion - Technical Spec, Build, Test - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000003, 'Conversion - Technical Spec, Build, Test - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000000, 'Source Data Extraction (Adapter Build)', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000000, 'Data Gap Analysis', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000000, 'Data Cleansing Plan-P1', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000001, 'Data Cleansing Plan-P2', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000003, 'Conduct Post Go-Live Support', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000000, 'Data Mapping-P1', CURDATE(), CURDATE(),'No');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000001, 'Data Mapping-P2', CURDATE(), CURDATE(),'No');

/*PMO*/
INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'Implementation Strategy', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'Kick-Off Presentation', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'Project Estimates & Timeline', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'Project Plan Creation', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'Manage Project Plan - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000001, 'Manage Project Plan - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000002, 'Manage Project Plan - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000003, 'Manage Project Plan - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'Manage Project Logs - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000001, 'Manage Project Logs - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000002, 'Manage Project Logs - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000003, 'Manage Project Logs - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'Project Meetings - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000001, 'Project Meetings - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000002, 'Project Meetings - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000003, 'Project Meetings - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'Client Management - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000001, 'Client Management - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000002, 'Client Management - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000003, 1000003, 'Client Management - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000000, 'PMO Hub Coordination - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000001, 'PMO Hub Coordination - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000002, 'PMO Hub Coordination - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000003, 'PMO Hub Coordination - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000001, 'Knowledge Transfer Approach & Plan', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000002, 'Cutover Plan Develop', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000003, 'Cutover Plan Execute', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000003, 'Post Production Support Plan', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000004, 'Conduct Post Go-Live Support', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000005, 1000003, 'Client Management - P4', CURDATE(), CURDATE(),'No');

/*PMO HUB*/
INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000000, 'Project Governance Structure & Processes', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000000, 'Engagement Economics - Setup, Dashboard, eFit, MAP, R&Q, Invoicing, Close-Out - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000001, 'Engagement Economics - Setup, Dashboard, eFit, MAP, R&Q, Invoicing, Close-Out - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000002, 'Engagement Economics - Setup, Dashboard, eFit, MAP, R&Q, Invoicing, Close-Out - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000003, 'Engagement Economics - Setup, Dashboard, eFit, MAP, R&Q, Invoicing, Close-Out - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000000, 'Staff Management - Staff Mgmt, TPL Staff, Onboarding - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000001, 'Staff Management - Staff Mgmt, TPL Staff, Onboarding - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000002, 'Staff Management - Staff Mgmt, TPL Staff, Onboarding - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000003, 'Staff Management - Staff Mgmt, TPL Staff, Onboarding - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000000, 'Deliverable Management - Support - P1', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000001, 'Deliverable Management - Support - P2', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000002, 'Deliverable Management - Support - P3', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000003, 'Deliverable Management - Support - P4', CURDATE(), CURDATE(),'Yes');

INSERT INTO pwc_responsibility_metadata(work_stream_id,prototype_id,activity,create_date,update_date,is_pwc_responsibility_flag)
VALUES(1000006, 1000004, 'Conduct Post Go-Live Support', CURDATE(), CURDATE(),'Yes');