package com.example.ai_writer;

import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

public class AI {

    public static Python getPythonInstance()
    {
        return Python.getInstance();
    }

    public static String generateResponse(String sequence)
    {
        PyObject torch = getPythonInstance().getModule("torch");
        PyObject GPT2LMHeadModel = getPythonInstance().getModule("transformers.GPT2LMHeadModel");
        PyObject GPT2Tokenizer = getPythonInstance().getModule("transformers.GPT2Tokenizer");

        PyObject model = GPT2LMHeadModel.callAttr("from_pretrained", "gpt2");
        PyObject tokenizer = GPT2Tokenizer.callAttr("from_pretrained", "gpt2");

        PyObject inputs = tokenizer.callAttr("encode", sequence, new Kwarg("return_tensors", "pt")); //Type torch.Tensor
        PyObject outputs = model.callAttr("generate", inputs, new Kwarg("max_length",100), new Kwarg("do_sample",true));
        String text = tokenizer.callAttr("decode", outputs.asList().get(0), new Kwarg("skip_special_tokens", true)).toString();

        return text;
    }
}
