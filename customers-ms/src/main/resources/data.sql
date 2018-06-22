INSERT INTO Customer_Types (id, caption)
  VALUES  (nextval('type_id_seq'), 'Residential'),
          (nextval('type_id_seq'), 'Small/Medium Business'),
          (nextval('type_id_seq'), 'Enterprise') ON CONFLICT (caption) DO NOTHING;