from django.shortcuts import render

# Create your views here.

from django.http import HttpResponse


def index(request):
    return HttpResponse("<form><input type=\"text\" /> <button type=\"submit\">Submit</button></form>")
