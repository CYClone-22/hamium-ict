import logging
from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from werkzeug.exceptions import BadRequest

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://admin:today0430@database-1.cva6yg8oenlg.ap-northeast-2.rds.amazonaws.com/mentosdb'
app.config['SQLALCHEMY_ENGINE_OPTIONS'] = {'pool_pre_ping': True}
db = SQLAlchemy(app)

@app.errorhandler(BadRequest)
def handle_bad_request(e):
    return jsonify({'message': 'Invalid JSON format'}), 400

# 로그 설정
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger('phpsignup')

class Guest(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(255), unique=True, nullable=False)
    password = db.Column(db.String(255), nullable=False)
    gender = db.Column(db.Enum('male', 'female'))
    birthdate = db.Column(db.Date)

class Survey(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    guest_id = db.Column(db.Integer, db.ForeignKey('guest.id'), nullable=False)
    role = db.Column(db.String(20))
    activity = db.Column(db.String(20))
    experience_level = db.Column(db.Integer)
    gender = db.Column(db.Enum('male', 'female'))
    age_group = db.Column(db.Integer)
    location = db.Column(db.String(50))
    activity_level = db.Column(db.String(20))

# 회원가입 라우트
@app.route('/signup', methods=['GET', 'POST'])
def signup():
    data = request.get_json()
    email = data['email']
    password = data['password']
    gender = data['gender']
    birthdate = data['birthdate']
    
    new_guest = Guest(email=email, password=password, gender=gender, birthdate=birthdate)
    db.session.add(new_guest)
    db.session.commit()
    
    logger.info('User created: %s', email)
    return jsonify({"message": "User created successfully!"}), 201

# 설문조사 라우트
@app.route('/survey', methods=['GET', 'POST'])
def survey():
    data = request.get_json()
    guest_id = data['guest_id']
    role = data['role']
    activity = data['activity']
    experience_level = data['experience_level']
    gender = data['gender']
    age_group = data['age_group']
    location = data['location']
    activity_level = data['activity_level']
    
    new_survey = Survey(guest_id=guest_id, role=role, activity=activity, experience_level=experience_level, gender=gender,
                        age_group=age_group, location=location, activity_level=activity_level)
    db.session.add(new_survey)
    db.session.commit()
    
    logger.info('Survey submitted: %s', guest_id)
    return jsonify({"message": "Survey submitted successfully!"}), 201

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5001)