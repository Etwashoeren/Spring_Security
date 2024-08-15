import {CheckCertificationRequestDto, IdCheckRequestDto, SignInRequestDto, SignUpRequestDto} from "./request/auth";
import axios, {AxiosResponse} from "axios";
import {IdCheckResponseDto, SignInResponseDto, SignUpResponseDto} from "./response/auth";
import {ResponseDto} from "./response";
import EmailCertificationRequestDto from "./request/auth/email-certification.request.dto";
import EmailCertificationResponseDto from "./response/auth/email-certification.response.dto";


const responseHandler = <T> (response: AxiosResponse<any, any>) => {
    const responseBody: T = response.data;
    return responseBody;
}

const errorHandler = (error : any) => {
    if (!error.response || !error.response.data) return null;
    const responseBody: ResponseDto = error.response.data;
    return responseBody;
}

const DOMAIN = 'http://localhost:8080';

const API_DOMAIN = `${DOMAIN}/api/v1`;

const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;
const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
const ID_CHECK_URL = () => `${API_DOMAIN}/auth/id-check`;
const EMAIL_CERTIFICATION_URL = () => `${API_DOMAIN}/auth/email-certification`;
const CHECK_CERTIFICATION_URL = () => `${API_DOMAIN}/auth/check-certification`;

export const signUpRequest = async (requestBody: SignUpRequestDto) => {

    const result = await axios.post(SIGN_UP_URL(), requestBody)
        .then(responseHandler<SignUpResponseDto>)
        .catch(errorHandler);

    return result;
}
export const signInRequest = async (requestBody: SignInRequestDto) => {

    const result = await axios.post(SIGN_IN_URL(), requestBody)
        .then(responseHandler<SignInResponseDto>)
        .catch(errorHandler);

    return result;
}

export const idCheckRequest = async (requestBody: IdCheckRequestDto) => {
    const result = await axios.post(ID_CHECK_URL(), requestBody)
        .then(responseHandler<IdCheckResponseDto>)
        .catch(errorHandler);

    return result;
}

export const emailCertificationRequest = async (requestBody: EmailCertificationRequestDto) => {

    const result = await axios.post(EMAIL_CERTIFICATION_URL(), requestBody)
        .then(responseHandler<EmailCertificationResponseDto>)
        .catch(errorHandler);
    return result;

}

export const checkCertificationRequest = async (requestBody: CheckCertificationRequestDto) => {

    const result = await axios.post(CHECK_CERTIFICATION_URL(), requestBody)
        .then(responseHandler<EmailCertificationResponseDto>)
        .catch(errorHandler);
    return result
}

