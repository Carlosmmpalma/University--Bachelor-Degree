import requests

api_root_url='http://localhost:8001/sd/'

response = requests.get(api_root_url+'turma')
print(response.json())

response = requests.post(api_root_url+'turma/add', params = {'numero': '801', 'nome': 'Student'} )
response = requests.get(api_root_url+'turma')
print(response.json())
