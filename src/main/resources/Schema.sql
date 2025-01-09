CREATE TABLE IF NOT EXISTS Run (
   id SERIAL PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   startTime TIMESTAMP NOT NULL,
   endTime TIMESTAMP NOT NULL,
   miles INT NOT NULL,
   location VARCHAR(255)
);

-- Created the Jobs Section Schema

CREATE TABLE users (
       id UUID PRIMARY KEY,
       username VARCHAR(255) NOT NULL,
       email VARCHAR(255) UNIQUE NOT NULL,
       password VARCHAR(255) NOT NULL,
       profile JSONB,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE jobs (
      id UUID PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      description TEXT NOT NULL,
      company_name VARCHAR(255) NOT NULL,
      location VARCHAR(255) NOT NULL,
      job_type VARCHAR(50) NOT NULL CHECK (job_type IN ('Full-Time', 'Part-Time', 'Contract', 'Remote', 'Internship')),
      salary_range VARCHAR(255),
      tags JSONB,
      requirements TEXT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE tags (
      id UUID PRIMARY KEY,
      tag_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE applications (
      id UUID PRIMARY KEY,
      user_id UUID REFERENCES users(id) ON DELETE CASCADE,
      job_id UUID REFERENCES jobs(id) ON DELETE CASCADE,
      application_status VARCHAR(50) NOT NULL CHECK (application_status IN ('Applied', 'Interviewing', 'Rejected', 'Hired')),
      applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE preparation_material (
      id UUID PRIMARY KEY,
      job_id UUID REFERENCES jobs(id) ON DELETE CASCADE,
      content_type VARCHAR(50) NOT NULL CHECK (content_type IN ('Interview Questions', 'Skills Test', 'Study Material')),
      content TEXT NOT NULL, -- Generated content
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE search_logs (
     id UUID PRIMARY KEY,
     user_id UUID REFERENCES users(id) ON DELETE CASCADE,
     query TEXT NOT NULL,
     filters_applied JSONB,
     results_count INTEGER DEFAULT 0,
     search_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




