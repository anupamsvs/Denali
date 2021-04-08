CREATE TABLE app_user (
   id INT NOT NULL AUTO_INCREMENT,
   user_profile_id INT,
   sso_id VARCHAR(100),
   password VARCHAR(100),
   first_name VARCHAR(100),
   last_name  VARCHAR(100),
   email VARCHAR(100),
   PRIMARY KEY (id),
   UNIQUE (sso_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  user_profile(
   id INT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30),
   PRIMARY KEY (id),
   UNIQUE (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE persistent_logins (
    username VARCHAR(64),
    series VARCHAR(64),
    token VARCHAR(64),
    last_used TIMESTAMP,
    PRIMARY KEY (series)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE work_stream(
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE onsite_csp_rate_metadata(
	id INT NOT NULL AUTO_INCREMENT,
    work_stream_id INT,
    partner_cost DOUBLE,
    director_cost DOUBLE,
    manager_cost DOUBLE,
    senior_associate_cost DOUBLE,
    associate_cost DOUBLE,
    create_date DATETIME,
    update_date DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE onsite_csp_rate_transaction_data (
	id INT NOT NULL AUTO_INCREMENT,
    work_stream_id INT,
    engagement_id INT,
    partner_cost DOUBLE,
    director_cost DOUBLE,
    manager_cost DOUBLE,
    senior_associate_cost DOUBLE,
    associate_cost DOUBLE,
	update_date DATETIME,
    version INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE offshore_csp_rate_metadata(
	id INT NOT NULL AUTO_INCREMENT,
    work_stream_id INT,
    offshore_landed_cost DOUBLE,
    manager_cost DOUBLE,
    staff_cost DOUBLE,
    create_date DATETIME,
    update_date DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE offshore_csp_rate_transaction_data(
	id INT NOT NULL AUTO_INCREMENT,
    work_stream_id INT,
    engagement_id INT,
    offshore_landed_cost DOUBLE,
    manager_cost DOUBLE,
    staff_cost DOUBLE,
    update_date DATETIME,
    version INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE engagement(
	id INT NOT NULL AUTO_INCREMENT,
    guid VARCHAR(100),
    engagement_status VARCHAR(100),
    client_name VARCHAR(255),
    engagement_name VARCHAR(255),
    engagement_leader VARCHAR(255),
    engagement_leader_email VARCHAR(255),
    planned_project_start_date DATETIME,
    client_sector VARCHAR(255),
    estimation_requestor VARCHAR(255),
    estimation_requestor_email VARCHAR(255),
    estimation_requested_date DATETIME,
    comments VARCHAR(2000),
    business_units_number INT,
    project_duration DOUBLE,
    post_go_live_support_duration DOUBLE,
    create_date DATETIME,
    update_date DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE engagement_status(
	id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE engagement_share(
	id INT NOT NULL AUTO_INCREMENT,
    engagement_id INT,
    guid VARCHAR(100),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE business_process(
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE module_metadata(
    id INT NOT NULL AUTO_INCREMENT,
    business_process_id INT,
    module_name VARCHAR(255),
    module_abbreviation VARCHAR(100),
    module_duration_factor DOUBLE,
    module_weightage DOUBLE,
    ddc_supported_flag VARCHAR(50),
    create_date DATETIME,
    update_date DATETIME,
    is_in_scope_flag VARCHAR(50),
    complexity_factor FLOAT,
    number_of_requirements INT(11),
    number_of_gaps INT(11),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE module_transaction_data(
    id INT NOT NULL AUTO_INCREMENT,
    business_process_id INT,
    complexity_factor FLOAT,
    engagement_id INT NOT NULL,
    module_name VARCHAR(255),
    module_abbreviation VARCHAR(100),
    module_duration_factor DOUBLE,
    moduleweightage DOUBLE,
    ddc_supported_flag VARCHAR(50),
    is_in_scope_flag VARCHAR(50),
    number_of_requirements INT,
    number_of_gaps INT,
    update_date DATETIME,
    version INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE conversion_data_type(
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE client_complexity(
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE conversion_metadata(
    id INT NOT NULL AUTO_INCREMENT,
    conversion_data_type_id INT,
    module_id INT,
    conversion_name VARCHAR(255),
    create_date DATETIME,
    update_date DATETIME,
    scope_override_flag VARCHAR(50),
    conversion_source VARCHAR(255),
    conversion_volume DOUBLE,
    client_complexity_id INT(11),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE conversion_transaction_data(
    id INT NOT NULL AUTO_INCREMENT,
    conversion_data_type_id INT,
    business_process_id INT,
    module_id INT,
    client_complexity_id INT,
    engagement_id INT,
    conversion_name VARCHAR(255),
    scope_override_flag VARCHAR(50),
    final_scope_flag VARCHAR(50),
    conversion_source VARCHAR(255),
    conversion_volume DOUBLE,
    is_user_create_flag VARCHAR(50),
    update_date DATETIME,
    version INT,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rice_type(
    id INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE paas_app_type(
    id INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rice_transaction_data(
    id INT NOT NULL AUTO_INCREMENT,
    rice_type_id INT,
    paas_app_type_id INT,
    module_id INT,
    client_complexity_id INT,
    engagement_id INT,
    rice_name VARCHAR(255),
    rice_source_system VARCHAR(255) ,
    rice_target_system VARCHAR(255),
    technical_complexity_multiplier FLOAT,
    functional_complexity_multiplier FLOAT,
    create_date DATETIME,
    update_date DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE prototype(
    id INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pwc_responsibility_metadata(
    id INT NOT NULL AUTO_INCREMENT,
	work_stream_id INT,
    prototype_id INT,
    activity VARCHAR(2000),
    create_date DATETIME,
    update_date DATETIME,
    is_pwc_responsibility_flag VARCHAR(50),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pwc_responsibility_transaction_data(
    id INT NOT NULL AUTO_INCREMENT,
	work_stream_id INT,
    prototype_id INT,
	activity VARCHAR(2000),
    engagement_id INT,
    is_pwc_responsibility_flag VARCHAR(50),
    update_date DATETIME,
    version INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE service_owner(
    id INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE additional_service(
    id INT NOT NULL AUTO_INCREMENT,
	work_stream_id INT,
    service_owner_id INT,
    engagement_id INT,
    activity VARCHAR(2000),
    prototype_effort_p1_assessment DOUBLE,
    prototype_effort_p2_design_config DOUBLE,
    prototype_effort_p3_refine_validate DOUBLE,
    prototype_effort_p4_test_deploy DOUBLE,
    prototype_effort_post_go_live_support DOUBLE,
    create_date DATETIME,
    update_date DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE engagement_attachment(
    id INT NOT NULL AUTO_INCREMENT,
    engagement_id INT,
	file_name_original VARCHAR(255),
    file_name_on_server VARCHAR(255),
    file_path VARCHAR(10000),
    upload_time DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE engagement_enterprise_application(
    id INT NOT NULL AUTO_INCREMENT,
    engagement_id INT,
	enterprise_application_id INT,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE enterprise_application(
	id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE app_user AUTO_INCREMENT=1000000;
ALTER TABLE user_profile AUTO_INCREMENT=1000000;
ALTER TABLE work_stream AUTO_INCREMENT=1000000;
ALTER TABLE onsite_csp_rate_metadata AUTO_INCREMENT=1000000;
ALTER TABLE onsite_csp_rate_transaction_data AUTO_INCREMENT=1000000;
ALTER TABLE offshore_csp_rate_metadata AUTO_INCREMENT=1000000;
ALTER TABLE offshore_csp_rate_transaction_data AUTO_INCREMENT=1000000;
ALTER TABLE enterprise_application AUTO_INCREMENT=1000000;
ALTER TABLE engagement_enterprise_application AUTO_INCREMENT=1000000;
ALTER TABLE engagement AUTO_INCREMENT=1000000;
ALTER TABLE engagement_status AUTO_INCREMENT=1000000;
ALTER TABLE engagement_share AUTO_INCREMENT=1000000;
ALTER TABLE business_process AUTO_INCREMENT=1000000;
ALTER TABLE module_metadata AUTO_INCREMENT=1000000;
ALTER TABLE module_transaction_data AUTO_INCREMENT=1000000;
ALTER TABLE conversion_data_type AUTO_INCREMENT=1000000;
ALTER TABLE client_complexity AUTO_INCREMENT=1000000;
ALTER TABLE conversion_metadata AUTO_INCREMENT=1000000;
ALTER TABLE conversion_transaction_data AUTO_INCREMENT=1000000;
ALTER TABLE rice_type AUTO_INCREMENT=1000000;
ALTER TABLE paas_app_type AUTO_INCREMENT=1000000;
ALTER TABLE rice_transaction_data AUTO_INCREMENT=1000000;
ALTER TABLE prototype AUTO_INCREMENT=1000000;
ALTER TABLE pwc_responsibility_metadata AUTO_INCREMENT=1000000;
ALTER TABLE pwc_responsibility_transaction_data AUTO_INCREMENT=1000000;
ALTER TABLE service_owner AUTO_INCREMENT=1000000;
ALTER TABLE additional_service AUTO_INCREMENT=1000000;
ALTER TABLE engagement_attachment AUTO_INCREMENT=1000000;