select facility.*,facility_type.name as facility_type_name,rent_type.name as rent_type_name
from facility 
inner join facility_type on facility_type_id=facility_type.id
inner join rent_type on rent_type_id=rent_type.id
where facility.is_delete = 0;

update facility set `name` = ?, area = ?, cost = ?, max_people = ?, standard_room= ?, description_other_convenience = ?, pool_area = ?, number_of_floors = ?, facility_free = ?, rent_type_id = ?, facility_type_id = ? where is_delete = 0 and id = ?
