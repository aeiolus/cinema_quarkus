-- Create film table
CREATE TABLE IF NOT EXISTS film (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    notes TEXT,
    state VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create index on title for faster searches
CREATE INDEX IF NOT EXISTS idx_film_title ON film(title);

-- Create index on state for faster filtering
CREATE INDEX IF NOT EXISTS idx_film_state ON film(state);

-- Create function to update updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create trigger to automatically update updated_at
CREATE TRIGGER update_film_updated_at
    BEFORE UPDATE ON film
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();


-- Insert sample film
INSERT INTO film (title, notes, state) VALUES
    ('The Shawshank Redemption', 'A tale of hope and perseverance', 'OPEN'),
    ('The Godfather', 'An offer you can''t refuse', 'ACTIVE'),
    ('Pulp Fiction', 'Interconnected tales of violence and redemption', 'ACTIVE'),
    ('The Dark Knight', 'When an unstoppable force meets an immovable object', 'INACTIVE'),
    ('Fight Club', 'First rule: you do not talk about it', 'ACTIVE');

CREATE TABLE IF NOT EXISTS showtime (
    id SERIAL PRIMARY KEY,
    film_id BIGINT NOT NULL,
    start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (film_id) REFERENCES film(id) ON DELETE CASCADE
);

-- Create index for faster date-based queries
CREATE INDEX IF NOT EXISTS idx_showtime_start_time ON showtime(start_time);

-- -- Function to generate sample showtime for each movie
-- CREATE OR REPLACE FUNCTION generate_sample_showtime()
-- RETURNS void AS $$
-- DECLARE
--     film_rec RECORD;
--     _current_date DATE;
--     start_date DATE;
--     end_date DATE;
--     showtime1 TIME;
--     showtime2 TIME;
--     base_times TIME[] := ARRAY['14:30:00'::TIME, '18:00:00'::TIME, '20:30:00'::TIME];
--     time_offset INTERVAL;
--     film_count INTEGER;
-- BEGIN
--     -- Set date range (1 year before and 1 year after current date)
--     start_date := CURRENT_DATE - INTERVAL '1 year';
--     end_date := CURRENT_DATE + INTERVAL '1 year';
    
--     -- Count total number of film
--     SELECT COUNT(*) INTO film_count FROM film;
    
--     -- Exit if no film exist
--     IF film_count = 0 THEN
--         RAISE NOTICE 'No film found in the database';
--         RETURN;
--     END IF;
    
--     -- Loop through each film
--     FOR film_rec IN SELECT id FROM film LOOP
--         -- Loop through each day in the date range
--         _current_date := start_date;
        
--         WHILE _current_date <= end_date LOOP
--             -- Calculate time offset based on film_id to distribute movies throughout the day
--             time_offset := (film_rec.id % film_count) * INTERVAL '30 minutes';
            
--             -- Create at least two showtime for each film per day
--             FOR i IN 1..array_length(base_times, 1) LOOP
--                 -- Add the showtime with the offset
--                 INSERT INTO showtime (film_id, start_time)
--                 VALUES (
--                     film_rec.id,
--                     (_current_date + base_times[i] + time_offset)::timestamp with time zone
--                 );
--             END LOOP;
            
--             _current_date := _current_date + INTERVAL '1 day';
--         END LOOP;
--     END LOOP;
-- END;
-- $$ LANGUAGE plpgsql;

-- -- Execute the function to generate sample data
-- SELECT generate_sample_showtime();

-- -- Drop the function after use since it's only needed for initialization
-- DROP FUNCTION IF EXISTS generate_sample_showtime();
