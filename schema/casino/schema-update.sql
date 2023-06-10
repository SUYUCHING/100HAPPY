
insert into t_entity (f_id,f_name,f_code)
values (nextval('seq_entity_id'),'PyP','pyp');


insert into t_domain_setting (f_id,f_domain, f_auto_approve_reg,f_type,f_entity_id)
values (nextval('seq_domain_setting_id'),'localhost',10,10,1);

INSERT INTO public.t_antertainment_city(
	f_id, f_code, f_name, f_data)
	VALUES (nextval('seq_antertainment_city_id'), 'Leo', '九州', ''),
	(nextval('seq_antertainment_city_id'), '大富翁', '大富翁', '');

INSERT INTO public.t_antertainment_game_category(
	f_id, f_code, f_name, f_antertainment_city_id)
	VALUES (nextval('seq_antertainment_game_category_id'), 'KU真人', 'KU真人', 1),
	(nextval('seq_antertainment_game_category_id'), 'DU真人', 'DU真人', 2);

INSERT INTO public.t_antertainment_game_type(
	f_id, f_code, f_name, f_antertainment_game_category_id)
	VALUES (nextval('seq_antertainment_game_type_id'), '中華百家', '中華百家', 1),
	(nextval('seq_antertainment_game_type_id'), '線上百家', '線上百家', 2);
	
INSERT INTO public.t_antertainment_game(
	f_id, f_code, f_name,f_type, f_antertainment_game_type_id)
	VALUES (nextval('seq_antertainment_game_id'), '中華百家A', '中華百家A',10, 1),
	(nextval('seq_antertainment_game_id'), '中華百家B', '中華百家B',10, 1),
		(nextval('seq_antertainment_game_id'), '中華百家C', '中華百家C',10, 1),
			(nextval('seq_antertainment_game_id'), '中華百家D', '中華百家D',10, 1),
				(nextval('seq_antertainment_game_id'), '中華百家E', '中華百家E',10, 1),
					(nextval('seq_antertainment_game_id'), '中華百家F', '中華百家F',10, 1),
					(nextval('seq_antertainment_game_id'), '線上百家A', '線上百家A',10, 2), 
					(nextval('seq_antertainment_game_id'), '線上百家B', '線上百家B',10, 2);
	