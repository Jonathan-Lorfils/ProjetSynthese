import React, { useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import _ from 'lodash'
import bsCustomFileInput from 'bs-custom-file-input'
import axios from "axios"

const UploadNFT = () => {

  const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
  const [uploadFile, setUploadFile] = useState()
  const [uploadFileName, setUploadFileName] = useState()

  const submitForm = (event) => {
    event.preventDefault()

    if (typeof (uploadFile) !== 'undefined' && !_.isEmpty(uploadFileName)) {
      var fileSignature = uploadFileName + ":" + userInfo.id
      var fileSignatureJSON = JSON.stringify(fileSignature)
      const formData = new FormData()
      formData.append("uploadFile", uploadFile, fileSignatureJSON)

    axios
        .post("http://localhost:2022/uploadNft", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          },
        })
        .then((response) => {
          alert("Upload reussi")
        })
        .catch((error) => {
          alert("Upload echoue")
        })
    }
    console.log(uploadFile)
  }

    return (
      <div className="gradient-form gradient-custom-2">
        <CustomerNavbar />
        <div className="d-flex justify-content-center align-items-center h-75">
          <div className="jumbotron jumbotron-fluid bg-light rounded shadow">
            <form className="container-fluid" onSubmit={submitForm}>
              <h1 className="text-center text-secondary">Téléverser un NFT</h1>
              <div className="form-group">
                <label htmlFor="fileName" className="text-secondary"><i className="fas fa-file-pdf"></i> Nom du fichier :</label>
                <input type='text' className="form-control form-control-lg" id="fileName" name="fileName" onChange={(e) => setUploadFileName(e.target.value)} />
              </div>
              <div className="form-group">
                <div className="custom-file">
                  <input type="file" className="custom-file-input" accept="image/png, image/jpeg" id="customFileLangHTML" onChange={(e) => { setUploadFile(e.target.files[0]); bsCustomFileInput.init() }} />
                  <label className="custom-file-label" htmlFor="customFileLangHTML" data-browse="Parcourir">Sélectionner un fichier</label>
                </div>
              </div>
              <div className="d-flex justify-content-center">
                <button type="submit" className="btn btn-block btn-primary text-white ">Soumettre</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    )
  }

export default UploadNFT