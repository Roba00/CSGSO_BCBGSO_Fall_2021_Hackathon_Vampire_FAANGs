from flask import Flask, render_template
import torch
from transformers import GPT2LMHeadModel, GPT2Tokenizer
from urllib.parse import unquote

app = Flask(__name__)

@app.route('/')
@app.route('/<text>')
def hello_world(text=None):
    if (text):
        tokenizer = GPT2Tokenizer.from_pretrained('gpt2')
        model = GPT2LMHeadModel.from_pretrained('gpt2')

        sequence = unquote(text)
        inputs = tokenizer.encode(sequence, return_tensors='pt')
        outputs = model.generate(inputs, max_length=350, do_sample=True)
        text = tokenizer.decode(outputs[0], skip_special_tokens=True)

    return render_template("aiwriter.html", text=text)